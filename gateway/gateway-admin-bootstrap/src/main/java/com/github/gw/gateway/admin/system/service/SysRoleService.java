package com.github.gw.gateway.admin.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.gw.gateway.admin.system.domain.SysRole;
import com.github.gw.gateway.admin.system.vo.RoleVo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author li7hai26@gmail.com
 * @since 2017-10-29
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 通过用户ID，查询角色信息
     *
     * @param userId
     * @return
     */
    List<SysRole> findRolesByUserId(Long userId);

    /**
     * 根据角色ID 查询角色列表
     *
     * @param roleIdList 角色ID列表
     * @param key        缓存key
     * @return
     */
    List<SysRole> findRolesByRoleIds(List<Long> roleIdList, String key);

    /**
     * 通过角色ID，删除角色
     *
     * @param id
     * @return
     */
    Boolean removeRoleById(Long id);

    /**
     * 根据角色菜单列表
     *
     * @param roleVo 角色&菜单列表
     * @return
     */
    Boolean updateRoleMenus(RoleVo roleVo);

    /**
     * 根据角色code查找角色
     *
     * @param role
     * @return
     */
    SysRole findRoleByRoleCode(String role);
}
