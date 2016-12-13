package com.ballogomezharo.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


    @Service
    public class SecurityService {
        private AuthenticationManager authenticationManager;
        private UserDetailsService userDetailsService;

        public SecurityService(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
            this.authenticationManager = authenticationManager;
            this.userDetailsService = userDetailsService;
        }

        public static String findLoggedInUsername() {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            return auth.getName(); //get logged in username
        }

        public void login(String username, String password) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            System.out.println(username+", "+password);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());


            authenticationManager.authenticate(usernamePasswordAuthenticationToken);

            if (usernamePasswordAuthenticationToken.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            }

        }

    }
