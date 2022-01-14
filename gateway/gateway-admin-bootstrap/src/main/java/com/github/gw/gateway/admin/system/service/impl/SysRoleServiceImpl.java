package com.github.gw.gateway.admin.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.gw.common.core.constant.CacheConstants;
import com.github.gw.gateway.admin.system.domain.SysRole;
import com.github.gw.gateway.admin.system.domain.SysRoleMenu;
import com.github.gw.gateway.admin.system.mapper.SysRoleMapper;
import com.github.gw.gateway.admin.system.service.SysRoleMenuService;
import com.github.gw.gateway.admin.system.service.SysRoleService;
import com.github.gw.gateway.admin.system.vo.RoleVo;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author li7hai26@gmail.com
 * @since 2017-10-29
 */
@Service
@AllArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    private SysRoleMenuService roleMenuService;

    /**
     * 通过用户ID，查询角色信息
     *
     * @param userId
     * @return
     */
    @Override
    public List findRolesByUserId(Long userId) {
        return baseMapper.listRolesByUserId(userId);
    }

    /**
     * 根据角色ID 查询角色列表，注意缓存删除
     *
     * @param roleIdList 角色ID列表
     * @param key        缓存key
     * @return
     */
    @Override
    @Cacheable(value = CacheConstants.ROLE_DETAILS, key = "#key")
    public List<SysRole> findRolesByRoleIds(List<Long> roleIdList, String key) {
        return baseMapper.selectBatchIds(roleIdList);
    }

    /**
     * 通过角色ID，删除角色,并清空角色菜单缓存
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean removeRoleById(Long id) {
        roleMenuService.remove(Wrappers.<SysRoleMenu>update().lambda().eq(SysRoleMenu::getRoleId, id));
        return this.removeById(id);
    }

    /**
     * 根据角色菜单列表
     *
     * @param roleVo 角色&菜单列表
     * @return
     */
    @Override
    public Boolean updateRoleMenus(RoleVo roleVo) {
        SysRole sysRole = baseMapper.selectById(roleVo.getRoleId());
        return roleMenuService.saveRoleMenus(sysRole.getRoleCode(), roleVo.getRoleId(), roleVo.getMenuIds());
    }

    /**
     * 根据角色code查找角色
     *
     * @param role
     * @return
     */
    @Override
    public SysRole findRoleByRoleCode(String role) {
        SysRole sysRole = baseMapper.selectOne(Wrappers.<SysRole>query().lambda().eq(SysRole::getRoleCode, role));
        return sysRole;
    }

}
