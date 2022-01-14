package com.github.gw.gateway.admin.security.handler;

import com.github.gw.common.core.domain.R;
import com.github.gw.common.core.utils.WebmvcUtil;
import lombok.extern.slf4j.Slf4j;
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
        WebmvcUtil.out(response, R.fail("登录异常", e.getMessage()));
    }
}
