package com.zhst.config.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 重写认证失败之后的逻辑
 */
@Service
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    public CustomAuthenticationFailureHandler() {

    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        //显示具体什么原因导致认证失败
        //跳转到登录页面
        httpServletResponse.sendRedirect("/login/page");
    }
}
