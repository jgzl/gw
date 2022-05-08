package com.github.jgzl.gw.gateway.admin.security.handler;

import cn.hutool.core.collection.CollUtil;
import com.github.jgzl.gw.common.model.system.vo.LoginUserVo;
import com.github.jgzl.gw.common.core.constant.SecurityConstants;
import com.github.jgzl.gw.common.core.model.R;
import com.github.jgzl.gw.common.core.utils.JwtTokenUtil;
import com.github.jgzl.gw.common.core.utils.WebmvcUtil;
import com.github.jgzl.gw.gateway.admin.security.vo.ExtendUser;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author li7hai26@gmail.com
 * @date 2021/12/24
 */
public class LoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        ExtendUser user = (ExtendUser) authentication.getPrincipal();
        Map<String, Object> kvMap = Maps.newHashMap();
        kvMap.put(SecurityConstants.DETAILS_USER_ID, user.getUserId());
        kvMap.put(SecurityConstants.DETAILS_USERNAME, user.getUsername());
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
        WebmvcUtil.okOut(response, R.success(result));
    }
}
