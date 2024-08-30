package com.expensemanager.restapi.controller;

import com.expensemanager.restapi.entity.User;
import com.expensemanager.restapi.entity.UserModel;
import com.expensemanager.restapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userservice;


    //Get all users
    @GetMapping("/profile")
    public ResponseEntity<User> readUser()
    {
        return new ResponseEntity<User>(userservice.readUser(),HttpStatus.OK);
    }

    @PutMapping("/profile")
    public ResponseEntity<User> updateUser(@RequestBody UserModel user)
    {
        return new ResponseEntity<User>(userservice.updateUser(user),HttpStatus.OK);
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<HttpStatus> deleteUser()
    {
        userservice.deleteUser();
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }
}
