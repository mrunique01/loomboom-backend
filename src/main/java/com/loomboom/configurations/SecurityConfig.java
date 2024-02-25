package com.loomboom.configurations;

import static com.loomboom.contants.PathConstants.LOG_IN;
import static com.loomboom.contants.PathConstants.REGISTRATION;

import java.lang.reflect.Array;
import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.filter.CorsFilter;

import com.loomboom.filters.JwtFilter;
import com.loomboom.security.oauth2.OAuth2FailureHandler;
import com.loomboom.security.oauth2.OAuth2SuccessHandler;
import com.loomboom.service.impl.OAuth2Service;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationEntryPoint jwtException;

    private final JwtFilter jwtFilter;

    private final OAuth2Service oAuth2Service;

    private final OAuth2SuccessHandler oAuth2SuccessHandler;

    private final OAuth2FailureHandler oAuth2FailureHandler;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        security.csrf(csrf -> csrf.disable()).cors(cors -> cors.disable()).authorizeHttpRequests(
                auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS).permitAll()
                        .requestMatchers("/api/v1/user/**").hasRole("USER")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/oauth2/**").permitAll()
                        .requestMatchers("/**").permitAll()
                        .requestMatchers("/assets/images/**").permitAll()
                        .anyRequest().authenticated())
                .oauth2Login(oauth -> oauth.authorizationEndpoint(auth -> auth.baseUri("/oauth2/authorize"))
                        .userInfoEndpoint(userInfo -> userInfo.userService(oAuth2Service))
                        .successHandler(oAuth2SuccessHandler).failureHandler(oAuth2FailureHandler))
                .exceptionHandling(ex -> ex.authenticationEntryPoint(jwtException))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        security.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return security.build();

    }

    // @Bean
    // CorsFilter corsFilter() {
    //     CorsConfiguration corsConfiguration = new CorsConfiguration();
    //     corsConfiguration.setAllowCredentials(true);
    //     corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
    //     corsConfiguration.addAllowedMethod("*");
    //     corsConfiguration.addAllowedHeader("*");
    //     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //     source.registerCorsConfiguration("/**", corsConfiguration);
    //     source.registerCorsConfiguration("/assets/images/**", corsConfiguration);
    //     return new CorsFilter(source);
    // }
}
