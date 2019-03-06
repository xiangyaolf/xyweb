package com.zhst.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
/**
 * 自定义 AuthenticationProvider
 */
public class CustomAuthenticationProvider implements AuthenticationProvider {

    //采用自定义的UserDetailService从数据库加载UserDetail
    private CustomUserDetailService customUserDetailService;

    /**
     * 自定义认证逻辑
     *
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //登录时，用户输入的用户名
        String username = authentication.getName();
        //pwd
        String credentials = (String) authentication.getCredentials();

        UserDetails userDetails = customUserDetailService.loadUserByUsername(username);
        String usernameDb = userDetails.getUsername();
        String passwordDb = userDetails.getPassword();
        if (username.equals(usernameDb) && credentials.equals(passwordDb)) {
            //构建认证成功的token,并把相应的权限交给下一个流程（授权）
            return new UsernamePasswordAuthenticationToken(userDetails, authentication.getCredentials(), userDetails.getAuthorities());
        } else {
            //认证失败抛出异常
            throw new UsernameNotFoundException(String.format("No qualified user[%s]!", username));
        }
    }

    /**
     * 假设全部支持
     *
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    @Autowired
    public void setCustomUserDetailService(CustomUserDetailService customUserDetailService) {
        this.customUserDetailService = customUserDetailService;
    }
}
