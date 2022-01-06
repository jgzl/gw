package com.github.gw.gateway.admin.security.handler;

import cn.hutool.core.collection.CollUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.github.gw.common.core.constant.SecurityConstants;
import com.github.gw.common.core.domain.R;
import com.github.gw.common.core.utils.JacksonUtil;
import com.github.gw.common.core.utils.JwtTokenUtil;
import com.github.gw.common.core.utils.WebmvcUtil;
import com.github.gw.gateway.admin.security.vo.ExtendUser;
import com.github.gw.gateway.admin.system.vo.LoginUserVo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author li7hai26@gmail.com
 * @date 2021/12/24
 */
public class LoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        ExtendUser user = (ExtendUser) authentication.getPrincipal();
        ObjectMapper mapper = JacksonUtil.getInstance();
        Map<String, Object> kvMap = mapper.readValue(mapper.writeValueAsString(user),Map.class);
        kvMap.remove("roles");
        kvMap.remove("permissions");
        String token = JwtTokenUtil.createToken(kvMap);
        LoginUserVo result = new LoginUserVo();
        result.setToken(token);
        List<String> roles = Lists.newArrayList();
        List<String> permissions = Lists.newArrayList();
        result.setRoles(roles);
        result.setPermissions(permissions);
        result.setUserId(user.getUserId());
        result.setUsername(user.getUsername());
        result.setNickname(user.getNickName());
        Collection<GrantedAuthority> authorities = user.getAuthorities();
        if (CollUtil.isNotEmpty(authorities)) {
            authorities.stream().map(GrantedAuthority::getAuthority).forEach(authority -> {
                if (authority.startsWith(SecurityConstants.ROLE_PREFIX)) {
                    roles.add(authority);
                } else {
                    permissions.add(authority);
                }
            });
        }
        WebmvcUtil.out(response, R.ok(result));
    }
}
