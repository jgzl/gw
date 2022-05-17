package cn.cleanarch.gw.gateway.listener;

import cn.cleanarch.gw.common.core.constant.enums.RecordLogEnum;
import cn.cleanarch.gw.common.core.spi.ExtensionLoader;
import cn.cleanarch.gw.common.gateway.domain.GatewayLog;
import cn.cleanarch.gw.gateway.configuration.properties.GatewayProperties;
import cn.cleanarch.gw.gateway.spi.log.service.RecordLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 监听器模式用于解耦日志过滤器和日志写入功能
 *
 * @author li7hai26@gmail.com
 * @date 2021/12/17
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class GatewayRequestLogApplicationListener implements ApplicationListener<GatewayRequestLogApplicationEvent> {

    private final GatewayProperties gatewayProperties;

    @Override
    public void onApplicationEvent(GatewayRequestLogApplicationEvent event) {
        GatewayLog gatewayLog = event.getGatewayLog();
        log.info("开始日志入库");
        RecordLogService recordLogService;
        if (gatewayProperties.getLogType()==null) {
            recordLogService = ExtensionLoader.getExtensionLoader(RecordLogService.class).getJoin(RecordLogEnum.LocalFile.name());
        }else {
            recordLogService = ExtensionLoader.getExtensionLoader(RecordLogService.class).getJoin(gatewayProperties.getLogType().name());
        }
        recordLogService.recordLog(gatewayLog);
        log.info("结束日志入库");
    }
}
