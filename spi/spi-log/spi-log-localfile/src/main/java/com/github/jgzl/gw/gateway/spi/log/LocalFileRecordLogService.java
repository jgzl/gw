package com.github.jgzl.gw.gateway.spi.log;

import com.github.jgzl.gw.common.core.spi.Join;
import com.github.jgzl.gw.common.core.utils.JacksonUtil;
import com.github.jgzl.gw.common.gateway.domain.GatewayLog;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "gateway")
@Join
public class LocalFileRecordLogService implements RecordLogService {
    @Override
    public void recordLog(GatewayLog gatewayLog) {
        log.info(JacksonUtil.toJsonString(gatewayLog));
    }
}
