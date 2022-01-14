package com.github.gw.gateway.admin.security.handler;

import com.github.gw.common.core.domain.R;
import com.github.gw.common.core.utils.WebmvcUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author li7hai26@gmail.com
 * @date 2021/12/24
 */
@Slf4j
public class LoginAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        log.error("登录授权发生异常:", e);
        WebmvcUtil.out(response, R.fail("登录异常", e.getMessage()));
    }
}
