package cn.cleanarch.gw.gateway.spi.log.service;

import cn.cleanarch.gw.common.core.spi.Join;
import cn.cleanarch.gw.common.gateway.domain.GatewayLog;
import cn.cleanarch.gw.gateway.spi.log.repository.GatewayRequestLogReactiveElasticsearchRepository;
import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Join
public class ElasticsearchRecordLogService implements RecordLogService {
    @Override
    public void recordLog(GatewayLog gatewayLog) {
        GatewayRequestLogReactiveElasticsearchRepository repository = SpringUtil.getBean(GatewayRequestLogReactiveElasticsearchRepository.class);
        Mono<GatewayLog> logMono = repository.save(gatewayLog);
        logMono
                .doOnSuccess(s -> log.info("日志id为[{}],入库成功", gatewayLog.getId()))
                .doOnError(e -> log.error("日志id为[{}],入库异常:", gatewayLog.getId(), e))
                .subscribe();
    }
}
