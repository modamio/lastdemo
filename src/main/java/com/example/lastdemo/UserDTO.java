package com.example.lastdemo;

import lombok.Data;

@Data
public class UserDTO {
    private int id;

    private String email;

    private String fullName;

    private String password;

    public UserDTO(int id, String email, String fullName, String password) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
    }
    public UserDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.fullName = user.getFullName();
        this.password = user.getPassword();
    }
}
