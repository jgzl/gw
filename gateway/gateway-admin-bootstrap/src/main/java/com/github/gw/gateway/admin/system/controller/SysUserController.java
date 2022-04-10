package com.github.gw.gateway.admin.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.gw.common.core.model.R;
import com.github.gw.gateway.admin.security.utils.SecurityUtils;
import com.github.gw.common.model.system.domain.SysUser;
import com.github.gw.gateway.admin.system.service.SysUserService;
import com.github.gw.common.model.system.vo.SysUserDto;
import com.github.gw.common.model.system.vo.SysUserVo;
import com.github.gw.common.model.system.vo.UserInfo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 用户管理模块
 *
 * @author li7hai26@gmail.com
 * @date 2018/12/16
 */
@Valid
@RestController
@AllArgsConstructor
@RequestMapping("/system/user")
public class SysUserController {

    private final SysUserService userService;

    /**
     * 获取指定用户全部信息
     *
     * @param userName 用户名
     * @return 用户信息
     */
    @GetMapping("/info/{userName}")
    public R<UserInfo> infoByUserName(@NotEmpty @PathVariable String userName) {
        return R.success(userService.findUserInfo(userName));
    }

    /**
     * 获取当前用户全部信息
     *
     * @return 用户信息
     */
    @GetMapping(value = {"/info"})
    public R<UserInfo> info() {
        return R.success(userService.findUserInfo(SecurityUtils.getUser().getUsername()));
    }

    /**
     * 通过ID查询用户信息
     *
     * @param id ID
     * @return 用户信息
     */
    @GetMapping("/{id}")
    public R<SysUserVo> user(@PathVariable Integer id) {
        return R.success(userService.selectUserVoById(id));
    }

    /**
     * 根据用户名查询用户信息
     *
     * @param userName 用户名
     * @return
     */
    @GetMapping("/details/{userName}")
    public R<SysUser> user(@PathVariable String userName) {
        SysUser condition = new SysUser();
        condition.setUserName(userName);
        return R.success(userService.getOne(new QueryWrapper<>(condition)));
    }

    /**
     * 删除用户信息
     *
     * @param id ID
     * @return R
     */
    @DeleteMapping("/{id}")
    public R<Boolean> userDel(@PathVariable Long id) {
        SysUser sysUser = userService.getById(id);
        return R.success(userService.deleteUserById(sysUser));
    }

    /**
     * 添加用户
     *
     * @param userDto 用户信息
     * @return success/false
     */
    @PostMapping
    //@PreAuthorize("@pms.hasPermission('sys_user_add')")
    public R<Boolean> user(@RequestBody SysUserDto userDto) {
        return R.success(userService.saveUser(userDto));
    }

    /**
     * 更新用户信息
     *
     * @param userDto 用户信息
     * @return R
     */
    @PutMapping
    //@PreAuthorize("@pms.hasPermission('sys_user_edit')")
    public R<Boolean> updateUser(@Valid @RequestBody SysUserDto userDto) {
        return R.success(userService.updateUser(userDto));
    }

    /**
     * 更新用户信息-锁定状态
     *
     * @param userDto 用户信息
     * @return R
     */
    @PutMapping("/lockFlag")
    //@PreAuthorize("@pms.hasPermission('sys_user_edit')")
    public R<Boolean> updateUserForLockFlag(@Valid @RequestBody SysUserDto userDto) {
        return R.success(userService.updateUserForLockFlag(userDto));
    }

    /**
     * 分页查询用户
     *
     * @param page    参数集
     * @param userDTO 查询参数列表
     * @return 用户集合
     */
    @GetMapping("/page")
    public R<IPage<SysUserVo>> getUserPage(Page page, SysUserDto userDTO) {
        return R.success(userService.getUsersWithDeptPage(page, userDTO));
    }

    /**
     * 查询用户
     *
     * @param userDTO 查询参数列表
     * @return 用户集合
     */
    @GetMapping("/list")
    public R<List<SysUserVo>> getUserList(SysUserDto userDTO) {
        return R.success(userService.getUsersWithDept(userDTO));
    }

    /**
     * 修改个人信息
     *
     * @param userDto userDto
     * @return success/false
     */
    @PutMapping("/edit")
    public R<Boolean> updateUserInfo(@Valid @RequestBody SysUserDto userDto) {
        return userService.updateUserInfo(userDto);
    }

    /**
     * 上级部门用户列表
     *
     * @param userName 用户名称
     * @return
     */
    @GetMapping("/ancestor/{userName}")
    public R<List<SysUser>> listAncestorUsers(@PathVariable String userName) {
        return R.success(userService.listAncestorUsers(userName));
    }

}
