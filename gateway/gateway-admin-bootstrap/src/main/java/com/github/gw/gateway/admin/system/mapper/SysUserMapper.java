package com.github.gw.gateway.admin.system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.gw.common.data.mapper.ExtendBaseMapper;
import com.github.gw.gateway.admin.system.domain.SysUser;
import com.github.gw.gateway.admin.system.vo.SysUserDto;
import com.github.gw.gateway.admin.system.vo.SysUserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author li7hai26@gmail.com
 * @since 2017-10-29
 */
@Mapper
public interface SysUserMapper extends ExtendBaseMapper<SysUser> {

	/**
	 * 通过用户名查询用户信息（含有角色信息）
	 * @param username 用户名
	 * @return sysUserVo
	 */
	SysUserVo getUserVoByUsername(String username);

	/**
	 * 分页查询用户信息（含角色）
	 * @param page 分页
	 * @param userDTO 查询参数
	 * @return list
	 */
	IPage<SysUserVo> getUserVosPage(Page page, @Param("query") SysUserDto userDTO);

	/**
	 * 查询用户信息（含角色）
	 * @param userDTO 查询参数
	 * @return list
	 */
	List<SysUserVo> getUserVosPage(@Param("query") SysUserDto userDTO);

	/**
	 * 通过ID查询用户信息
	 * @param id 用户ID
	 * @return sysUserVo
	 */
	SysUserVo getUserVoById(Integer id);

}
