package com.ananda.signupout.model;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class TwoFA {
    private String email;
    private int otp;
}
