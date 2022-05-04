package com.github.jgzl.gw.gateway.admin.security.handler;

import com.github.jgzl.gw.common.core.model.R;
import com.github.jgzl.gw.common.core.utils.WebmvcUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author li7hai26@gmail.com
 * @date 2021/12/24
 */
@Slf4j
public class LoginAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        log.error("登录认证发生异常:", e);
        WebmvcUtil.errorOut(response, R.error("登录异常", e.getMessage()));
    }
}
