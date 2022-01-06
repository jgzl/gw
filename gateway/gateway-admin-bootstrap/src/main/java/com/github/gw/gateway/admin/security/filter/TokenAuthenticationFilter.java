package com.github.gw.gateway.admin.security.filter;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.jwt.JWTPayload;
import com.github.gw.common.core.constant.SecurityConstants;
import com.github.gw.common.core.constant.TokenConstants;
import com.github.gw.common.core.domain.R;
import com.github.gw.common.core.exception.TokenException;
import com.github.gw.common.core.utils.JwtTokenUtil;
import com.github.gw.common.core.utils.WebmvcUtil;
import com.github.gw.gateway.admin.security.configuration.PermitAllUrlResolver;
import com.github.gw.gateway.admin.security.service.ExtendUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * token认证授权模块
 * @author li7hai26@gmail.com
 * @date 2021/12/24
 */
@Slf4j
public class TokenAuthenticationFilter extends BasicAuthenticationFilter {

    private PermitAllUrlResolver permitAllUrlResolver;

    public TokenAuthenticationFilter(AuthenticationManager authenticationManager, PermitAllUrlResolver permitAllUrlResolver) {
        super(authenticationManager);
        this.permitAllUrlResolver = permitAllUrlResolver;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("请求URL为:{}", request.getRequestURI());
        //不需要鉴权
        if (permitAllUrlResolver.match(request.getRequestURI())) {
            chain.doFilter(request, response);
        }
        UsernamePasswordAuthenticationToken authentication = null;
        try {
            authentication = getAuthentication(request);
        } catch (Exception e) {
            WebmvcUtil.out(response, R.fail(e,e.getMessage()));
            return;
        }
        if (authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            WebmvcUtil.out(response, R.fail("鉴权失败"));
            return;
        }
        chain.doFilter(request, response);
    }

    /**
     * 从token中获取用户登陆信息
     * @param request
     * @return
     */
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = getToken(request);
        if (StrUtil.isNotBlank(token)) {
            if (JwtTokenUtil.verify(token)) {
                throw new TokenException("token已失效,请重新登陆");
            }else {
                // 从Token中解密获取用户名
                JWTPayload jwtPayload = JwtTokenUtil.getPayLoad(token);
                String userName = Convert.toStr(jwtPayload.getClaim(SecurityConstants.DETAILS_USERNAME),SecurityConstants.DEFAULT_USER);
                ExtendUserDetailsService service = SpringUtil.getBean(ExtendUserDetailsService.class);
                if (StrUtil.isNotBlank(userName)) {
                    UserDetails userDetails = service.loadUserByUsername(userName);
                    if (userDetails!=null) {
                        return new UsernamePasswordAuthenticationToken(userName, token, userDetails.getAuthorities());
                    }
                    return null;
                }
                return null;
            }
        }
        return null;
    }

    /**
     * 从请求中获取token
     * @param request
     * @return
     */
    private String getToken(HttpServletRequest request) {
        // 获取Token字符串，token 置于 header , cookie ,request param里
        String token = request.getHeader(TokenConstants.TOKEN_HEADER);
        if (!StringUtils.hasText(token)) {
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(TokenConstants.TOKEN_COOKIE)) {
                    token = cookie.getValue();
                }
            }
            if (!StringUtils.hasText(token)) {
                token = request.getParameter(TokenConstants.TOKEN_PARAMETER);
            }
        }else {
            // 移除Bearer 字段
            token = token.substring(7,token.length()-1);
        }
        return token;
    }
}