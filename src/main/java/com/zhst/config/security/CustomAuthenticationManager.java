package com.zhst.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class CustomAuthenticationManager implements AuthenticationManager {

    //自己提供的认证
    private AuthenticationProvider customAuthenticationProvider;

    @Autowired
    public CustomAuthenticationManager(AuthenticationProvider authenticationProvider) {
        this.customAuthenticationProvider = authenticationProvider;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Authentication authenticate = customAuthenticationProvider.authenticate(authentication);
        if (null == authenticate) {
            throw new ProviderNotFoundException("Authentication failed!");
        }
        return authenticate;
    }
}
