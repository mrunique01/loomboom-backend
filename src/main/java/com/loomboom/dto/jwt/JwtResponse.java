package com.loomboom.dto.jwt;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {

    private String email;
    private String token;

}
