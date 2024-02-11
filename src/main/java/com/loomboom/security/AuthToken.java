package com.loomboom.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import com.loomboom.model.Role;

@Component
public class AuthToken {

    public UsernamePasswordAuthenticationToken getAuthToken(String email, String password, List<Role> roles) {
        Collection<? extends GrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().toString())).collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(email, password, authorities);
    }

}
