package com.loomboom.security.oauth2;

import com.loomboom.model.User;
import com.loomboom.service.UserService;
import com.loomboom.utils.jwt.JwtHelper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtHelper jwtHelper;
    private final UserService userService;

    @Value("${hostname}")
    private String hostname;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getName();
        User user = userService.findByEmail(email);
        user.setAttributes(oAuth2User.getAttributes());
        String token = jwtHelper.createToken(user);
        String uri = UriComponentsBuilder.fromUriString("http://" + hostname + "/oauth2/redirect")
                .queryParam("token", token)
                .queryParam("user-id", user.getId())
                .queryParam("success", true)
                .build().toUriString();
        getRedirectStrategy().sendRedirect(request, response, uri);
    }
}
