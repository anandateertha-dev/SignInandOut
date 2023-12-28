package com.ananda.signupout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ananda.signupout.Services.UserService;
import com.ananda.signupout.model.User;
import com.ananda.signupout.model.VerifyUser;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api")
public class MyController {

    @Autowired
    private UserService service;

    //To add a user
    @PostMapping("adduser")
    public ResponseEntity<Object> addUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        return service.userAddService(user, bindingResult);
    }

    // Log In API
    @GetMapping("verifyuser")
    public ResponseEntity<Object> verifyUser(@RequestBody VerifyUser verifyUser) {
        return service.verifyingTheUserService(verifyUser);
    }

    //Get user details using token
    @GetMapping("userdetailsbytoken")
    public ResponseEntity<Object> getUserDetailsByEmail(@RequestHeader String token) {
        return service.getUserDetailsByEmailService(token);
    }

    //Forgot password
    @PostMapping("forgotpassword")
    public ResponseEntity<Object> forgotPassword(@RequestHeader String email) {
        return service.forgotPasswordService(email);
    }

    //To verify otp
    @PostMapping("verifyOtp")
    public ResponseEntity<Object> verifyTheUserOtp(@RequestHeader String otp) {
        return service.verifyTheOtpEnteredByUser(otp);
    }

    //To reset password
    @PostMapping("resetpassword")
    public ResponseEntity<Object> resetThePassword(@RequestHeader String passwordFromUser) {
        return service.resetThePasswordService(passwordFromUser);
    }

    //Two factor authentication
    @PostMapping("2fa")
    public ResponseEntity<Object> twofa(@RequestHeader int otpforTwoFAFromUser) {
        return service.TwoFAService(otpforTwoFAFromUser);
    }
}
