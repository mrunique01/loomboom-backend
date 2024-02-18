package com.loomboom.mapper;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import com.loomboom.dto.jwt.JwtRequest;
import com.loomboom.dto.jwt.JwtResponse;
import com.loomboom.dto.user.RegisterRequest;
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
    private final CommonMapper commonMapper;

    public JwtResponse logIn(JwtRequest jwtRequest) {
        authenticationService.signin(jwtRequest.getEmail(), jwtRequest.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getEmail());
        User user = userService.findByEmail(userDetails.getUsername());
        String token = jwtHelper.createToken(user);
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setId(user.getId());
        jwtResponse.setEmail(userDetails.getUsername());
        jwtResponse.setToken(token);
        return jwtResponse;
    }

    public JwtResponse registerUser(RegisterRequest registerRequest) {
        User user = commonMapper.mapObject(registerRequest, User.class);
        user = authenticationService.signup(user);
        String token = jwtHelper.createToken(user);
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setId(user.getId());
        jwtResponse.setEmail(user.getEmail());
        jwtResponse.setToken(token);
        return jwtResponse;
    }

}
