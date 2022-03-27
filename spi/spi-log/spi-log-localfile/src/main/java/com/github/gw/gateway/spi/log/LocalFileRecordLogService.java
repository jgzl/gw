package com.github.gw.gateway.spi.log;

import com.alibaba.fastjson.JSON;
import com.github.gw.common.core.spi.Join;
import com.github.gw.common.gateway.domain.GatewayLog;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "gateway")
@Join
public class LocalFileRecordLogService implements RecordLogService {
    @Override
    public void recordLog(GatewayLog gatewayLog) {
        log.info(JSON.toJSONString(gatewayLog));
    }
}
