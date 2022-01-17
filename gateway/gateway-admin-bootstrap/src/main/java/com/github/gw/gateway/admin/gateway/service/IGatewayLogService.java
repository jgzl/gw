package com.github.gw.gateway.admin.gateway.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.gw.common.gateway.vo.GatewayLogVo;

/**
 * @author li7hai26@gmail.com
 * @date 2021/12/22
 */
public interface IGatewayLogService {
    Page<GatewayLogVo> getByGatewayRequestLog(Page<GatewayLogVo> page,GatewayLogVo gatewayRequestLog);
}
