package com.saicmotor.engine.gateway.admin.gateway.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.saicmotor.engine.common.core.constant.CommonConstants;
import com.saicmotor.engine.gateway.admin.gateway.convert.AccessConvert;
import com.saicmotor.engine.gateway.admin.gateway.domain.GatewayAccessConf;
import com.saicmotor.engine.gateway.admin.gateway.mapper.GatewayAccessConfMapper;
import com.saicmotor.engine.gateway.admin.gateway.service.GatewayAccessConfService;
import com.saicmotor.engine.gateway.admin.gateway.vo.GatewayAccessConfVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * 网关访问
 * @author li7hai26@gmail.com
 * @date 2018年11月06日10:27:55
 */
@Slf4j
@AllArgsConstructor
@Service("gatewayAccessConfService")
public class GatewayAccessConfServiceImpl extends ServiceImpl<GatewayAccessConfMapper, GatewayAccessConf>
		implements GatewayAccessConfService {

	/**
	 * 新增或者更新
	 * @param item
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean saveOrUpdate(GatewayAccessConf item) {
		item.setDelFlag(CommonConstants.STATUS_NORMAL);
		return super.saveOrUpdate(item);
	}

	/**
	 * 删除
	 * @param id id
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteItem(String id) {
		super.removeById(id);
	}

	@Override
	public Boolean updateStatus(GatewayAccessConfVo vo) {
		GatewayAccessConf domain = AccessConvert.INSTANCE.convertVo2Do(vo);
		domain.setUpdateTime(LocalDateTime.now());
		return super.updateById(domain);
	}
}
