package com.loomboom.mapper;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import com.loomboom.dto.jwt.JwtRequest;
import com.loomboom.dto.jwt.JwtResponse;
import com.loomboom.model.User;
import com.loomboom.service.AuthenticationService;
import com.loomboom.service.UserService;
import com.loomboom.utils.jwt.JwtHelper;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthenticationMapper {

    private final AuthenticationService authenticationService;
    private final UserDetailsService userDetailsService;
    private final JwtHelper jwtHelper;
    private final UserService userService;


    public JwtResponse logIn(JwtRequest jwtRequest) {
        authenticationService.signin(jwtRequest.getEmail(), jwtRequest.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getEmail());
        User user = userService.findByEmail(userDetails.getUsername());
        String token = jwtHelper.createToken(user);
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setEmail(userDetails.getUsername());
        jwtResponse.setToken(token);
        return jwtResponse;
    }

}
