package com.github.gw.gateway.admin.gateway.service;

import com.github.gw.common.gateway.domain.GatewayLog;
import com.github.gw.common.gateway.vo.GatewayLogVo;

import java.util.List;

/**
 * @author li7hai26@gmail.com
 * @date 2021/12/22
 */
public interface IGatewayLogService {
    List<GatewayLog> getByGatewayRequestLog(GatewayLogVo gatewayRequestLog);
}
