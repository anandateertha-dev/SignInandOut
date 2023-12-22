package com.ananda.signupout.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Component
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;
    
    @NotNull
    @Size(min = 3,max = 15)
    private String nameOfUser;

    @NotNull
    @Size(min = 3,max = 15)
    private String userName;

    @NotNull
    @Email(message = "Enter a valid email")
    private String email;
    private String password;

    public String getNameOfUser() {
        return nameOfUser;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setNameOfUser(String nameOfUser) {
        this.nameOfUser = nameOfUser;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
