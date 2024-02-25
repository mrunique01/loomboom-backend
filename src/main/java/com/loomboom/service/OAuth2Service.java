package com.loomboom.service;

import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.loomboom.model.User;
import com.loomboom.security.oauth2.OAuth2UserInfo;

public interface OAuth2Service {

    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException;

    public User registerOauth2User(String provider, OAuth2UserInfo oAuth2UserInfo);

    public User updateOauth2User(User user, String provider, OAuth2UserInfo oAuth2UserInfo);

}
