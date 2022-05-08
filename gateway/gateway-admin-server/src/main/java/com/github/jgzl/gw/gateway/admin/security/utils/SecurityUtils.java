package com.github.jgzl.gw.gateway.admin.security.utils;

import cn.hutool.core.util.StrUtil;
import com.github.jgzl.gw.common.core.constant.SecurityConstants;
import com.github.jgzl.gw.gateway.admin.security.vo.ExtendUser;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author li7hai26@gmail.com
 * @date 2021/12/24
 */

/**
 * 安全工具类
 *
 * @author L.cm
 */
@UtilityClass
public class SecurityUtils {

    /**
     * 获取Authentication
     */
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取用户
     *
     * @param authentication
     * @return ExtendUser
     * <p>
     */
    public ExtendUser getUser(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof ExtendUser) {
            return (ExtendUser) principal;
        }
        return null;
    }

    /**
     * 获取用户
     */
    public ExtendUser getUser() {
        Authentication authentication = getAuthentication();
        return getUser(authentication);
    }

    /**
     * 获取用户角色信息
     *
     * @return 角色集合
     */
    public List<String> getRoles() {
        Authentication authentication = getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        List<String> roleList = new ArrayList<>();
        authorities.stream().filter(granted -> StrUtil.startWith(granted.getAuthority(), SecurityConstants.ROLE_PREFIX))
                .forEach(granted -> roleList.add(granted.getAuthority()));
        return roleList;
    }

}
