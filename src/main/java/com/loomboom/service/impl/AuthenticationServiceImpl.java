package com.loomboom.service.impl;

import static com.loomboom.contants.ErrorMessage.INVALID_EMAIL_PASSWORD;
import static com.loomboom.contants.ErrorMessage.USER_EXISTS;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.loomboom.exceptions.ApiRequestException;
import com.loomboom.model.Role;
import com.loomboom.model.User;
import com.loomboom.repository.UserRepository;
import com.loomboom.service.AuthenticationService;
import com.loomboom.service.RoleService;
import static com.loomboom.enums.RoleEnum.USER;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final UserRepository userRepository;

    @Override
    public Boolean signin(String email, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            authenticationManager.authenticate(authentication);
            return true;
        } catch (BadCredentialsException e) {
            System.out.println(e.getMessage() + "here420");
            throw new ApiRequestException(INVALID_EMAIL_PASSWORD, HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public User signup(User user) {
        if (userRepository.findByEmail(user.getEmail()).orElse(null) != null) {
            throw new ApiRequestException(USER_EXISTS, HttpStatus.BAD_REQUEST);
        }
        Role role = roleService.findByName(USER);
        if (role == null) {
            role = roleService.save(USER);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of(role));
        return userRepository.save(user);
    }

}
