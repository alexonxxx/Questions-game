package com.ballogomezharo.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


@Component
public class WebSecurity {

    public boolean checkUserId(Authentication authentication, String id) {
        return id.equals(authentication.getName());
    }


}
