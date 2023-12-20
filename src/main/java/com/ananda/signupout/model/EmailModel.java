package com.ananda.signupout.model;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class EmailModel {
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
}
