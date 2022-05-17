package cn.cleanarch.gw.gateway.spi.log.service;

import cn.cleanarch.gw.common.core.spi.SPI;
import cn.cleanarch.gw.common.gateway.domain.GatewayLog;

@SPI
public interface RecordLogService {
    void recordLog(GatewayLog gatewayLog);
}
