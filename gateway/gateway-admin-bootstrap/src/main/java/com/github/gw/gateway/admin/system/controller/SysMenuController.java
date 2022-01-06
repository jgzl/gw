package com.github.gw.gateway.admin.system.controller;

import cn.hutool.core.lang.tree.Tree;
import com.github.gw.common.core.domain.R;
import com.github.gw.gateway.admin.security.utils.SecurityUtils;
import com.github.gw.gateway.admin.system.domain.SysMenu;
import com.github.gw.gateway.admin.system.service.SysMenuService;
import com.github.gw.gateway.admin.system.service.SysRoleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 菜单管理模块
 * @author li7hai26@gmail.com
 * @date 2017/10/31
 */
@RestController
@AllArgsConstructor
@RequestMapping("/system/menu")
public class SysMenuController {

	private final SysMenuService sysMenuService;

	private final SysRoleService sysRoleService;

	/**
	 * 返回当前用户的树形菜单集合
	 * @param type 类型
	 * @param parentId 父节点ID
	 * @return 当前用户的树形菜单
	 */
	@GetMapping
	public R<List<Tree<Long>>> getUserMenu(String type, Long parentId) {

		// 获取符合条件的菜单
		Set<SysMenu> all = new HashSet<>();
		SecurityUtils.getRoles().stream()
				.map(sysRoleService::findRoleByRoleCode)
				.forEach(sysRole -> all.addAll(sysMenuService.findMenuByRoleId(sysRole.getRoleId())));
		return R.ok(sysMenuService.filterMenu(all, type, parentId));
	}

	/**
	 * 返回树形菜单集合
	 * @param lazy 是否是懒加载
	 * @param parentId 父节点ID
	 * @return 树形菜单
	 */
	@GetMapping(value = "/tree")
	public R<List<Tree<Long>>> getTree(boolean lazy, Long parentId) {
		return R.ok(sysMenuService.treeMenu(lazy, parentId));
	}

	/**
	 * 返回角色的菜单集合
	 * @param roleId 角色ID
	 * @return 属性集合
	 */
	@GetMapping("/tree/{roleId}")
	public R<List<Long>> getRoleTree(@PathVariable Long roleId) {
		return R.ok(
				sysMenuService.findMenuByRoleId(roleId).stream().map(SysMenu::getMenuId).collect(Collectors.toList()));
	}

	/**
	 * 通过ID查询菜单的详细信息
	 * @param id 菜单ID
	 * @return 菜单详细信息
	 */
	@GetMapping("/{id}")
	public R<SysMenu> getById(@PathVariable Long id) {
		return R.ok(sysMenuService.getById(id));
	}

	/**
	 * 新增菜单
	 * @param sysMenu 菜单信息
	 * @return success/false
	 */
	@PostMapping
	//@PreAuthorize("@pms.hasPermission('sys_menu_add')")
	public R<SysMenu> save(@Valid @RequestBody SysMenu sysMenu) {
		sysMenuService.save(sysMenu);
		return R.ok(sysMenu);
	}

	/**
	 * 删除菜单
	 * @param id 菜单ID
	 * @return success/false
	 */
	@DeleteMapping("/{id}")
	//@PreAuthorize("@pms.hasPermission('sys_menu_del')")
	public R<Boolean> removeById(@PathVariable Integer id) {
		return sysMenuService.removeMenuById(id);
	}

	/**
	 * 更新菜单
	 * @param sysMenu 菜单对象
	 * @return
	 */
	@PutMapping
	//@PreAuthorize("@pms.hasPermission('sys_menu_edit')")
	public R<Boolean> update(@Valid @RequestBody SysMenu sysMenu) {
		return R.ok(sysMenuService.updateMenuById(sysMenu));
	}

}