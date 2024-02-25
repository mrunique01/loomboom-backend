package com.loomboom.service;

import com.loomboom.model.User;

public interface AuthenticationService {

    Boolean signin(String email, String password);

    User signup(User user, String captcha);

}
