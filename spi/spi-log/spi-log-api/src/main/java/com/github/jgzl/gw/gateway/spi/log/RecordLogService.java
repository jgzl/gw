package com.github.jgzl.gw.gateway.spi.log;

import com.github.jgzl.gw.common.gateway.domain.GatewayLog;
import com.github.jgzl.gw.common.core.spi.SPI;

@SPI
public interface RecordLogService {
    public void recordLog(GatewayLog gatewayLog);
}
