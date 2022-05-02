package com.github.jgzl.gw.common.model.system.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 用户信息
 *
 * @author li7hai26@gmail.com
 * @date 2021/12/24
 */
@Data
public class UserInfo implements Serializable {

    /**
     * 用户基本信息
     */
    private SysUserVo sysUser;

    /**
     * 权限标识集合
     */
    private List<String> permissions;

    /**
     * 角色集合
     */
    private List<String> roles;

}
