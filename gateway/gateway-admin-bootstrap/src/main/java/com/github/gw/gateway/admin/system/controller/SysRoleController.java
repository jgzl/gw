package com.github.gw.gateway.admin.system.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.gw.common.core.constant.CacheConstants;
import com.github.gw.common.core.domain.R;
import com.github.gw.gateway.admin.system.domain.SysRole;
import com.github.gw.gateway.admin.system.service.SysRoleService;
import com.github.gw.gateway.admin.system.vo.RoleVo;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 角色管理模块
 *
 * @author li7hai26@gmail.com
 * @date 2020-02-10
 */
@RestController
@AllArgsConstructor
@RequestMapping("/system/role")
public class SysRoleController {

    private final SysRoleService sysRoleService;

    /**
     * 通过ID查询角色信息
     *
     * @param id ID
     * @return 角色信息
     */
    @GetMapping("/{id}")
    public R<SysRole> getById(@PathVariable Integer id) {
        return R.ok(sysRoleService.getById(id));
    }

    /**
     * 添加角色
     *
     * @param sysRole 角色信息
     * @return success、false
     */
    @PostMapping
    //@PreAuthorize("@pms.hasPermission('sys_role_add')")
    @CacheEvict(value = CacheConstants.ROLE_DETAILS, allEntries = true)
    public R<Boolean> save(@Valid @RequestBody SysRole sysRole) {
        return R.ok(sysRoleService.save(sysRole));
    }

    /**
     * 修改角色
     *
     * @param sysRole 角色信息
     * @return success/false
     */
    @PutMapping
    //@PreAuthorize("@pms.hasPermission('sys_role_edit')")
    @CacheEvict(value = CacheConstants.ROLE_DETAILS, allEntries = true)
    public R<Boolean> update(@Valid @RequestBody SysRole sysRole) {
        return R.ok(sysRoleService.updateById(sysRole));
    }

    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    //@PreAuthorize("@pms.hasPermission('sys_role_del')")
    @CacheEvict(value = CacheConstants.ROLE_DETAILS, allEntries = true)
    public R<Boolean> removeById(@PathVariable Long id) {
        return R.ok(sysRoleService.removeRoleById(id));
    }

    /**
     * 获取角色列表
     *
     * @return 角色列表
     */
    @GetMapping("/list")
    public R<List<SysRole>> listRoles() {
        return R.ok(sysRoleService.list(Wrappers.emptyWrapper()));
    }

    /**
     * 分页查询角色信息
     *
     * @param page 分页对象
     * @param role 查询条件
     * @return 分页对象
     */
    @GetMapping("/page")
    public R<IPage<SysRole>> getRolePage(Page page, SysRole role) {
        Page result = sysRoleService.page(page, Wrappers.query(role));
        return R.ok(result);
    }

    /**
     * 更新角色菜单
     *
     * @param roleVo 角色对象
     * @return success、false
     */
    @PutMapping("/menu")
    //@PreAuthorize("@pms.hasPermission('sys_role_perm')")
    public R<Boolean> saveRoleMenus(@RequestBody RoleVo roleVo) {
        return R.ok(sysRoleService.updateRoleMenus(roleVo));
    }

    /**
     * 通过角色ID 查询角色列表
     *
     * @param roleIdList 角色ID
     * @return
     */
    @PostMapping("/getRoleList")
    public R<List<SysRole>> getRoleList(@RequestBody List<Long> roleIdList) {
        return R.ok(sysRoleService.findRolesByRoleIds(roleIdList, CollUtil.join(roleIdList, StrUtil.UNDERLINE)));
    }

}
