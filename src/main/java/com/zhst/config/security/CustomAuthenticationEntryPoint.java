package com.zhst.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

/**
 * 设置登录页的 URL
 */
@Configuration
public class CustomAuthenticationEntryPoint {

    @Bean
    public LoginUrlAuthenticationEntryPoint loginUrlAuthenticationEntryPoint() {
        return new LoginUrlAuthenticationEntryPoint("/login/page");
    }
}
