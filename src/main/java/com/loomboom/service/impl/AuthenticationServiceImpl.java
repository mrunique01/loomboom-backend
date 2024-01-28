package com.loomboom.service.impl;

import static com.loomboom.contants.ErrorMessage.INVALID_EMAIL_PASSWORD;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import com.loomboom.exceptions.ApiRequestException;
import com.loomboom.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{

    private final AuthenticationManager authenticationManager;

    @Override
    public Boolean signin(String email, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            authenticationManager.authenticate(authentication);
            return true;
        } catch (BadCredentialsException e) {
            throw new ApiRequestException(INVALID_EMAIL_PASSWORD, HttpStatus.UNAUTHORIZED);
        }
    }

}
