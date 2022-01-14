package com.github.gw.gateway.admin.system.vo;

import lombok.Data;

/**
 * @author lengleng
 * @date 2021/2/10
 */
@Data
public class RoleVo {

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 菜单列表
     */
    private String menuIds;

}
