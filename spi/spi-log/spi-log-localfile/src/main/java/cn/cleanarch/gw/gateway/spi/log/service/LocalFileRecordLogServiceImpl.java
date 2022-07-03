package cn.cleanarch.gw.gateway.spi.log.service;

import cn.cleanarch.gw.common.core.spi.Join;
import cn.cleanarch.gw.common.core.utils.JacksonUtil;
import cn.cleanarch.gw.common.gateway.domain.GatewayLog;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "gateway")
@Join
public class LocalFileRecordLogServiceImpl implements RecordLogService {
    @Override
    public void recordLog(GatewayLog gatewayLog) {
        log.info(JacksonUtil.toJsonString(gatewayLog));
    }
}
