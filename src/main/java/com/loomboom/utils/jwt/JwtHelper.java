package com.loomboom.utils.jwt;

import com.loomboom.model.User;

public interface JwtHelper {

    public String createToken(User user);

    public String getEmailFromToken(String token);

    public Boolean validateToken(String token, User user);

}
