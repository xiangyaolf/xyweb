package com.zhst.config.security;

import com.zhst.config.security.acl.CustomAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 自定义security安全配置
 */
@Configuration
@EnableWebSecurity
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter implements ApplicationContextAware {

    //自定义登录页
    private AuthenticationEntryPoint authenticationEntryPoint;

    //自定义认证过滤器
    private CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter;

    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Autowired
    public CustomSecurityConfig(AuthenticationEntryPoint authenticationEntryPoint,
                                CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter,
                                CustomAccessDeniedHandler customAccessDeniedHandler) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.customUsernamePasswordAuthenticationFilter = customUsernamePasswordAuthenticationFilter;
        this.customAccessDeniedHandler = customAccessDeniedHandler;
    }

    //自定义拦截配置
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                //异常出现的时候，定义返回到哪个页面
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(customAccessDeniedHandler)
                .and()
                .headers().frameOptions().sameOrigin()
                .and()
                .sessionManagement().invalidSessionUrl("/session_timeout")
                .and()
                //使用自定义的filter 替换UsernamePasswordAuthenticationFilter --必须使用完整类名，因为注册时候是通过完整类名去加载的
                //否则查找的还是默认的 Filter
                .addFilterAt(customUsernamePasswordAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                //设置/hello所有人都可以访问
                .antMatchers("/hello").permitAll()
                //放权session超时页面
                .antMatchers("/session_timeout").permitAll()
                .antMatchers("/index").hasRole("USER")
                .antMatchers("/system/**").hasRole("ADMIN")
                .antMatchers("/anonymous/**").anonymous()
                .anyRequest()
                .authenticated()
                .and()
                //表单登录
                .formLogin()
                //自定义登录页
                .loginPage("/login/page")
                //允许所有人进行访问此路径
                .permitAll()
                .and()
                //定义退出功能
                .logout()
                .logoutSuccessUrl("/login/page")
                //默认是true，不想清除session就设置为false
                .invalidateHttpSession(false)
                .and()
                //关闭csrf保护
                .csrf().disable();

    }

}
