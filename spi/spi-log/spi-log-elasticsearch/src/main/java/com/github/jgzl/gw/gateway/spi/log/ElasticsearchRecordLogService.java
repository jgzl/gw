package com.github.jgzl.gw.gateway.spi.log;

import cn.hutool.extra.spring.SpringUtil;
import com.github.jgzl.gw.common.gateway.domain.GatewayLog;
import com.github.jgzl.gw.common.core.spi.Join;
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
