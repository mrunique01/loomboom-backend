package com.loomboom.service.impl;

import static com.loomboom.contants.ErrorMessage.EMPTY_PROFILE;
import static com.loomboom.contants.ErrorMessage.USER_EXISTS;
import static com.loomboom.enums.RoleEnum.ROLE_USER;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.loomboom.enums.AuthProvider;
import com.loomboom.exceptions.ApiRequestException;
import com.loomboom.model.Role;
import com.loomboom.model.User;
import com.loomboom.repository.UserRepository;
import com.loomboom.security.oauth2.OAuth2UserFactory;
import com.loomboom.security.oauth2.OAuth2UserInfo;
import com.loomboom.service.RoleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OAuth2Service extends DefaultOAuth2UserService {

    private final RoleService roleService;
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        String provider = userRequest.getClientRegistration().getRegistrationId();
        OAuth2User oAuth2User = super.loadUser(userRequest);
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserFactory.getOAuth2UserInfo(provider, oAuth2User.getAttributes());
        User user = userRepository.findByEmail(oAuth2UserInfo.getEmail()).orElse(null);
        user.setAttributes(oAuth2User.getAttributes());
        if (user == null) {
            user = registerOauth2User(provider, oAuth2UserInfo);
        } else {
            user = updateOauth2User(user, provider, oAuth2UserInfo);
        }
        return user;
    }

    public User registerOauth2User(String provider, OAuth2UserInfo oAuth2UserInfo) {
        User user = new User();
        user.setEmail(oAuth2UserInfo.getEmail());
        user.setFirstName(oAuth2UserInfo.getFirstName());
        user.setLastName(oAuth2UserInfo.getLastName());
        if (oAuth2UserInfo.getEmail() == null) {
            throw new ApiRequestException(EMPTY_PROFILE, HttpStatus.UNAUTHORIZED);
        }
        Role role = roleService.findByName(ROLE_USER);
        if (role == null) {
            role = roleService.save(ROLE_USER);
        }
        user.setRoles(List.of(role));
        user.setProvider(AuthProvider.valueOf(provider.toUpperCase()));
        return userRepository.save(user);
    }

    public User updateOauth2User(User user, String provider, OAuth2UserInfo oAuth2UserInfo) {
        user = userRepository.findById(user.getId()).orElse(null);
        if (user == null) {
            throw new ApiRequestException(USER_EXISTS, HttpStatus.UNAUTHORIZED);
        }
        user.setFirstName(oAuth2UserInfo.getFirstName());
        user.setLastName(oAuth2UserInfo.getLastName());
        user.setProvider(AuthProvider.valueOf(provider.toUpperCase()));
        return userRepository.save(user);
    }

}
