package com.loomboom.dto.jwt;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {

    private Long id;
    private String email;
    private String token;

}
