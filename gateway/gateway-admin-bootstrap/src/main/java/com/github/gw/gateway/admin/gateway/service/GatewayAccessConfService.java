package com.github.gw.gateway.admin.gateway.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.gw.common.model.gateway.domain.GatewayAccessConf;
import com.github.gw.common.model.gateway.vo.GatewayAccessConfVo;

/**
 * 路由
 *
 * @author li7hai26@gmail.com
 * @date 2018-11-06 10:17:18
 */
public interface GatewayAccessConfService extends IService<GatewayAccessConf> {

    /**
     * 删除路由信息
     *
     * @param id 路由id
     * @return
     */
	void deleteItem(String id);

    Boolean updateStatus(GatewayAccessConfVo vo);
}
