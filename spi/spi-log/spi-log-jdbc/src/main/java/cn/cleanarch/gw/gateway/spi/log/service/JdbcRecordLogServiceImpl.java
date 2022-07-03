package cn.cleanarch.gw.gateway.spi.log.service;

import cn.cleanarch.gw.common.core.spi.Join;
import cn.cleanarch.gw.common.gateway.domain.GatewayLog;
import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author vlfac
 */
@Slf4j
@Join
public class JdbcRecordLogServiceImpl implements RecordLogService {
    @Override
    public void recordLog(GatewayLog gatewayLog) {
        GatewayLogService service = SpringUtil.getBean(JdbcGatewayLogServiceImpl.class);
        try {
            service.save(gatewayLog);
            log.info("日志id为[{}],入库成功", gatewayLog.getId());
        }catch (Exception e) {
            log.error("日志id为[{}],入库异常:", gatewayLog.getId(),e);
        }
    }
}
