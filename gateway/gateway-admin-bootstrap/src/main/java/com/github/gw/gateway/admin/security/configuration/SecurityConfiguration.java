package com.github.gw.gateway.admin.security.configuration;

import com.anji.captcha.service.CaptchaService;
import com.github.gw.common.core.constant.SecurityConstants;
import com.github.gw.gateway.admin.security.filter.TokenAuthenticationFilter;
import com.github.gw.gateway.admin.security.filter.TokenLoginFilter;
import com.github.gw.gateway.admin.security.handler.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


/**
 * 安全认证配置累
 * 启用方法级别的权限认证
 *
 * @author li7hai26@gmail.com
 * @date 2021/12/24
 */
@Slf4j
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final CaptchaService captchaService;
    private final PermitAllUrlResolver permitAllUrlResolver;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public LoginAuthenticationSuccessHandler loginAuthenticationSuccessHandler() {
        return new LoginAuthenticationSuccessHandler();
    }

    @Bean
    public LoginAuthenticationFailureHandler loginAuthenticationFailureHandler() {
        return new LoginAuthenticationFailureHandler();
    }

    @Bean
    public LoginAuthenticationEntryPoint loginAuthenticationEntryPoint() {
        return new LoginAuthenticationEntryPoint();
    }

    @Bean
    public LoginAccessDeniedHandler loginAccessDeniedHandler() {
        return new LoginAccessDeniedHandler();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new ExtendLogoutSuccessHandler();
    }

    /**
     * 不拦截静态资源
     *
     * @param web
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/index.html", "/favicon.ico", "/css/**", "/js/**", "/images/**", "/fonts/**", "/error", "/webjars/**");
    }

    // 核心配置
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        permitAllUrlResolver.registry(http.authorizeRequests());
        // 禁用 csrf, 由于使用的是JWT，我们这里不需要csrf
        http.cors().and().csrf().disable()
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // 基于token，所以不需要session
                .and()
                .formLogin() //关闭登录
                .loginProcessingUrl(SecurityConstants.LOGIN_PATH)
                .permitAll()
                .and()
                .authorizeRequests()
                // 其他所有请求需要身份认证
                .anyRequest().authenticated()
                .and()
                .headers().frameOptions().disable()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(loginAuthenticationEntryPoint())
                .accessDeniedHandler(loginAccessDeniedHandler());

        // 开启登录认证流程过滤器
        TokenLoginFilter tokenLoginFilter = new TokenLoginFilter(authenticationManager(), captchaService);
        TokenAuthenticationFilter tokenAuthenticationFilter = new TokenAuthenticationFilter(authenticationManager(), permitAllUrlResolver);
        tokenLoginFilter.setAuthenticationSuccessHandler(loginAuthenticationSuccessHandler());
        tokenLoginFilter.setAuthenticationFailureHandler(loginAuthenticationFailureHandler());
        http.addFilterAt(tokenLoginFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterAt(tokenAuthenticationFilter, BasicAuthenticationFilter.class);
        // 退出登录处理器
        // http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
        http.logout().logoutUrl(SecurityConstants.LOGOUT_PATH).logoutSuccessHandler(logoutSuccessHandler());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

}
