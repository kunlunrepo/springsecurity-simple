package com.sec.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * description :
 *
 * @author kunlunrepo
 * date :  2023-08-22 11:45
 */
@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler successHandler;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler failureHandler;

    @Autowired
    private KaptchaFilter kaptchaFilter;

    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests() // 开始配置授权，即允许哪些请求访问系统
                .mvcMatchers("/login.html").permitAll() // 指定哪些请求路径允许访问 permitAll 放行该资源，该资源为公共资源
                .mvcMatchers("/index").permitAll() // 指定哪些请求路径允许访问
                .anyRequest().authenticated() // 除上述以外，指定其他所有请求都需要经过身份验证
                .and()
                .formLogin() // 配置表单登录
                .loginPage("/login.html") // 登录页面
                .loginProcessingUrl("/login") // 提交路径
                .usernameParameter("username") // 表单中用户名
                .passwordParameter("password") // 表单中密码
                .successForwardUrl("/index") // 指定登录成功后跳转的路径
                .failureUrl("/login.html") // 指定登录失败后要跳转的路径
                .and()
                .csrf().disable(); // 关闭CSRF
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests() // 开始配置授权，即允许哪些请求访问系统
                .mvcMatchers("/login.html", "/code/image").permitAll() // 指定哪些请求路径允许访问 permitAll 放行该资源，该资源为公共资源
                .mvcMatchers("/index").permitAll() // 指定哪些请求路径允许访问
                .anyRequest().authenticated() // 除上述以外，指定其他所有请求都需要经过身份验证
                .and()
                .formLogin() // 配置表单登录
                .successHandler(successHandler)
                .failureHandler(new AuthenticationFailureHandlerImpl())
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(logoutSuccessHandler)
                .and()
                .csrf().disable(); // 关闭CSRF

        // 将自定义图形验证码校验过滤器，添加到UserNamePasswordAuthenticationFilter之前
        http.addFilterBefore(kaptchaFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
