package com.expensemanager.restapi.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserModel {
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotNull(message="Email cannot be empty")
    @Email(message="Enter a valid email address")
    private String email;

    @NotNull(message="Password cannot be empty")
    @Size(min=5,message="Password should be minimum of 5 characters")
    private String password;

    private Long age=0L;
}
