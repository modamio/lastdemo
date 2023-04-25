package com.example.lastdemo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="users")
public class User {
    @Id
    private int id;
    private String email;
    @Column(name = "full_name")
    private String fullName;
    private String password;

    public User(int id, String email, String fullName, String password) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
    }

    public  User(UserDTO userDTD){
        this.id = userDTD.getId();
        this.email = userDTD.getEmail();
        this.fullName = userDTD.getFullName();
        this.password = userDTD.getPassword();
    }
    public User(){}
}
