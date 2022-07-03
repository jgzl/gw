package cn.cleanarch.gw.gateway.spi.log.service;

import cn.cleanarch.gw.common.gateway.domain.GatewayLog;
import cn.cleanarch.gw.common.gateway.vo.GatewayLogVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * @author li7hai26@gmail.com
 * @date 2021/12/22
 */
public interface GatewayLogService {
    Page<GatewayLogVo> getByGatewayRequestLog(Page<GatewayLogVo> page, GatewayLogVo gatewayRequestLog);

    Iterable<GatewayLog> findAll();

    Iterable<GatewayLog> findAllById(List<String> idList);

    Iterable<GatewayLog> saveAll(List<GatewayLog> list);

    GatewayLog save(GatewayLog domain);

    void deleteAll();

    void deleteAllById(List<String> idList);
}
