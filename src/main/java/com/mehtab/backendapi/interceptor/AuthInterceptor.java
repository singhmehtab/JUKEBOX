package com.mehtab.backendapi.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The type Auth interceptor.
 */
@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String taxAccessToken = getTaxAccessToken(request);
        if (!taxAccessToken.isEmpty()) {
            return validateAccessToken(taxAccessToken, response);
        }
        log.info("401 Missing authentication keys");
        response.setStatus(401);
        return false;
    }

    private boolean validateAccessToken(String accessToken, HttpServletResponse response) {
        if(accessToken.equals("apiToken")){
            log.info("ACCESS_TOKEN:: {} is valid", accessToken);
            return true;
        }
        log.info("Authentication Failed for ACCESS_TOKEN: {}", accessToken);
        response.setStatus(401);
        return false;
    }


    private String getTaxAccessToken(HttpServletRequest request) {
        return request.getHeader("ACCESS_TOKEN");
    }
}
