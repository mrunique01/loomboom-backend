package com.loomboom.service;

import java.util.Map;

import jakarta.mail.MessagingException;

public interface EmailService {

    void SendMail(String to, String subject, String template, Map<String, Object> attributes) throws MessagingException;

}
