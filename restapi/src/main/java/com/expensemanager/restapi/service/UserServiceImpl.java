package com.expensemanager.restapi.service;

import com.expensemanager.restapi.entity.User;
import com.expensemanager.restapi.entity.UserModel;
import com.expensemanager.restapi.exception.ItemAlreadyExistsException;
import com.expensemanager.restapi.exception.ResourceNotFoundException;
import com.expensemanager.restapi.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private PasswordEncoder bcryptEncoder;
    @Autowired
    private UserRepository userrepository;

    @Override
    public User createUser(UserModel user) {
        if(userrepository.existsByEmail(user.getEmail()))
        {
            throw new ItemAlreadyExistsException("User is already registered with email :"+user.getEmail());
        }
        User newUser=new User();
        BeanUtils.copyProperties(user,newUser);
        newUser.setPassword(bcryptEncoder.encode(newUser.getPassword()));
        return userrepository.save(newUser);
    }

    @Override
    public User readUser() {
        Long userId=getLoggedInUser().getId();
        return userrepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found for the id:"+userId));
    }

    @Override
    public User updateUser(UserModel user) {
        User existingUser=readUser();
        existingUser.setName(user.getName()!=null? user.getName() : existingUser.getName());
        existingUser.setEmail(user.getEmail()!=null? user.getEmail() : existingUser.getEmail());
        existingUser.setPassword(user.getPassword()!=null? bcryptEncoder.encode(user.getPassword()) : existingUser.getPassword());
        existingUser.setAge(user.getAge()!=null? user.getAge() : existingUser.getAge());
        return userrepository.save(existingUser);
    }

    @Override
    public void deleteUser() {
        User existingUser=readUser();
        userrepository.delete(existingUser);
    }

    @Override
    public User getLoggedInUser() {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String email= authentication.getName();
        return userrepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User not found for the email"+email));
    }
}
