package com.loomboom.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.loomboom.dto.jwt.JwtRequest;
import com.loomboom.dto.jwt.JwtResponse;
import com.loomboom.mapper.AuthenticationMapper;
import static com.loomboom.contants.PathConstants.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationMapper authenticationMapper;

    @PostMapping(LOG_IN)
    public ResponseEntity<JwtResponse> logIn(@RequestBody JwtRequest jwtRequest) {
        JwtResponse jwtResponse = authenticationMapper.logIn(jwtRequest);
        return ResponseEntity.ok(jwtResponse);
    }

}
