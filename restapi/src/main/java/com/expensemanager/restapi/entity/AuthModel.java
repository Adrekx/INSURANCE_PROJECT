package com.expensemanager.restapi.entity;

import lombok.Data;

@Data
public class AuthModel {
    private String email;
    private String password;
}
