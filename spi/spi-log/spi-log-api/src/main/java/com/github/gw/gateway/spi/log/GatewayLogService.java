package com.github.gw.gateway.spi.log;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.gw.common.gateway.domain.GatewayLog;
import com.github.gw.common.gateway.vo.GatewayLogVo;

import java.util.List;

/**
 * @author li7hai26@gmail.com
 * @date 2021/12/22
 */
public interface GatewayLogService {
    Page<GatewayLogVo> getByGatewayRequestLog(Page<GatewayLogVo> page,GatewayLogVo gatewayRequestLog);

    Iterable<GatewayLog> findAll();

    Iterable<GatewayLog> findAllById(List<String> idList);

    Iterable<GatewayLog> saveAll(List<GatewayLog> list);

    void deleteAll();

    void deleteAllById(List<String> idList);
}
