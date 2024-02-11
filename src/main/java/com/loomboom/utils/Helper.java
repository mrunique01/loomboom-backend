package com.loomboom.utils;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Helper {

    private final HttpServletRequest request;

    public String getCurrentServerUrl() {
        // Get the current protocol (http or https)
        String protocol = request.getScheme();
        // Get the server name and port
        String serverName = request.getServerName();
        // Construct the server URL
        return protocol + "://" + serverName + "/";
    }
}
