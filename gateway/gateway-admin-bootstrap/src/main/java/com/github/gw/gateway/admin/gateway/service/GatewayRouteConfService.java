package com.github.gw.gateway.admin.gateway.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.gw.common.model.gateway.domain.GatewayRouteConf;

/**
 * 路由
 *
 * @author li7hai26@gmail.com
 * @date 2018-11-06 10:17:18
 */
public interface GatewayRouteConfService extends IService<GatewayRouteConf> {

    /**
     * 删除路由信息
     *
     * @param routeId 路由id
     * @return
     */
	void deleteRoute(String routeId);
}
