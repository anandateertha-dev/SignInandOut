package com.ananda.signupout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ananda.signupout.Services.UserService;
import com.ananda.signupout.model.User;
import com.ananda.signupout.model.VerifyUser;

@RestController
@RequestMapping("api")
public class MyController {

    @Autowired
    private UserService service;
    
    @PostMapping("adduser")
    public ResponseEntity<Object> addUser(@RequestBody User user)
    {
        return service.userAddService(user);
    }

    @GetMapping("verifyuser")
    public ResponseEntity<String> verifyUser(@RequestBody VerifyUser verifyUser)
    {
        return service.verifyingTheUserService(verifyUser);
    }

    @GetMapping("userdetailsbyemail")
    public ResponseEntity<Object> getUserDetailsByEmail(@RequestHeader String email)
    {
        return service.getUserDetailsByEmailService(email);
    }
}
