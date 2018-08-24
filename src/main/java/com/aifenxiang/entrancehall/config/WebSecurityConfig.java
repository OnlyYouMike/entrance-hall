package com.aifenxiang.entrancehall.config;

import com.aifenxiang.entrancehall.controller.apiconmon.Api;
import com.aifenxiang.entrancehall.controller.service.verify.impl.AiUserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import static com.aifenxiang.entrancehall.controller.apiconmon.Api.USER;
import static com.aifenxiang.entrancehall.controller.apiconmon.Api.USER_MAPPING.*;

/**
 * @author: zj
 * @create: 2018-08-21 23:33
 **/
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean("userDetailsService")
    public UserDetailsService userDetailsService(){
       return new AiUserServiceImpl();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(USER + GETREGISTERCODE).permitAll()
                .antMatchers(USER + REGISTER).permitAll()
                .anyRequest().authenticated() //任何请求,登录后可以访问
                .and()
                .formLogin()
                .loginPage(USER+SIGN_IN)
                .failureUrl(USER+SIGN_IN+"?error")//登录失败 返回error
                .permitAll() //登录页面用户任意访问
                .and()
                .logout().permitAll(); //注销行为任意访问

    }

}
