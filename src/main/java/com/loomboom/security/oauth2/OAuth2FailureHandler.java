package com.loomboom.security.oauth2;

import com.loomboom.model.User;
import com.loomboom.service.UserService;
import com.loomboom.utils.jwt.JwtHelper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2FailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Value("${hostname}")
    private String hostname;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {

        String uri = UriComponentsBuilder.fromUriString("http://" + hostname + "/oauth2/redirect")
                .queryParam("success", false)
                .queryParam("message", "Something Went Wrong")

                .build().toUriString();
        getRedirectStrategy().sendRedirect(request, response, uri);
    }
}
