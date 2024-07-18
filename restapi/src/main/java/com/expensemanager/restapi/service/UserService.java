package com.expensemanager.restapi.service;

import com.expensemanager.restapi.entity.User;
import com.expensemanager.restapi.entity.UserModel;

public interface UserService {
    User createUser(UserModel user);
    User readUser();
    User updateUser(UserModel user);
    void deleteUser();
    User getLoggedInUser();
}
