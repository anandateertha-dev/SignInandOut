package com.ananda.signupout.model;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ResponseMessage {
    private Boolean success;
    private String message;
}
