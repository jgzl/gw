package cn.cleanarch.gw.common.security.service;

import cn.cleanarch.gw.common.core.model.R;
import cn.cleanarch.gw.common.model.system.domain.SysUser;
import cn.cleanarch.gw.common.model.system.vo.SysUserDto;
import cn.cleanarch.gw.common.model.system.vo.SysUserVo;
import cn.cleanarch.gw.common.model.system.vo.UserInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author li7hai26@gmail.com
 * @date 2017/10/31
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 查询用户信息
     *
     * @param userName 用户
     * @return userInfo
     */
    UserInfo findUserInfo(String userName);

    /**
     * 查询用户信息
     *
     * @param sysUser 用户
     * @return userInfo
     */
    UserInfo findUserInfo(SysUser sysUser);

    /**
     * 分页查询用户信息（含有角色信息）
     *
     * @param page    分页对象
     * @param userDTO 参数列表
     * @return
     */
    IPage getUsersWithDeptPage(Page page, SysUserDto userDTO);

    /**
     * 删除用户
     *
     * @param sysUser 用户
     * @return boolean
     */
    Boolean deleteUserById(SysUser sysUser);

    /**
     * 更新当前用户基本信息
     *
     * @param userDto 用户信息
     * @return Boolean
     */
    R<Boolean> updateUserInfo(SysUserDto userDto);

    /**
     * 更新指定用户信息
     *
     * @param userDto 用户信息
     * @return
     */
    Boolean updateUser(SysUserDto userDto);

    /**
     * 通过ID查询用户信息
     *
     * @param id 用户ID
     * @return 用户信息
     */
    SysUserVo selectUserVoById(Integer id);

    /**
     * 查询上级部门的用户信息
     *
     * @param username 用户名
     * @return R
     */
    List<SysUser> listAncestorUsers(String username);

    /**
     * 保存用户信息
     *
     * @param userDto DTO 对象
     * @return success/fail
     */
    Boolean saveUser(SysUserDto userDto);

    List<SysUserVo> getUsersWithDept(SysUserDto userDTO);

    Boolean updateUserForLockFlag(SysUserDto userDto);
}
