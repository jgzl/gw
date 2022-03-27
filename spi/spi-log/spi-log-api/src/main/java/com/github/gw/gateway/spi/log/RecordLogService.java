package com.github.gw.gateway.spi.log;

import com.github.gw.common.core.spi.SPI;
import com.github.gw.common.gateway.domain.GatewayLog;

@SPI
public interface RecordLogService {
    public void recordLog(GatewayLog gatewayLog);
}
