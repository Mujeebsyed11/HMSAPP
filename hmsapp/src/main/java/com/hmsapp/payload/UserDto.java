package com.hmsapp.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class UserDto {
    private Long id;

    @NotNull(message = "Username is Mandatory")
    @Size(min = 2, max = 15, message = "Username should be 5 to 15 characters")
    private String username;

    @NotNull(message = "Email is mandatory")
    @Email(message = "Must be in email format")
    private String email;

    @NotNull(message = "Mobile Number is Mandatory")
    @Size(min =10, max =10, message = "Mobile number must have 10 digits")
    private String mobile;

    @NotNull(message = "Password is mandatory")
    @Size(min = 5, max = 20, message = "Password should be between 5 to 20 characters")
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {this.id = id;}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
