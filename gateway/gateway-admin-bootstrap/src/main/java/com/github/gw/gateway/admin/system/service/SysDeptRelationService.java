package com.github.gw.gateway.admin.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.gw.gateway.admin.system.domain.SysDept;
import com.github.gw.gateway.admin.system.domain.SysDeptRelation;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author li7hai26@gmail.com
 * @since 2018-02-12
 */
public interface SysDeptRelationService extends IService<SysDeptRelation> {

	/**
	 * 新建部门关系
	 * @param sysDept 部门
	 */
	void insertDeptRelation(SysDept sysDept);

	/**
	 * 通过ID删除部门关系
	 * @param id
	 */
	void deleteAllDeptRelation(Integer id);

	/**
	 * 更新部门关系
	 * @param relation
	 */
	void updateDeptRelation(SysDeptRelation relation);

}
