package com.github.gw.gateway.log.listener;

import com.alibaba.fastjson.JSON;
import com.github.gw.common.gateway.domain.GatewayLog;
import com.github.gw.gateway.common.ActionEnum;
import com.github.gw.gateway.log.repository.GatewayRequestLogReactiveElasticsearchRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * 监听器模式用于解耦日志过滤器和日志写入功能
 *
 * @author li7hai26@gmail.com
 * @date 2021/12/17
 */
@Slf4j
@Component
public class GatewayRequestLogApplicationListener implements ApplicationListener<GatewayRequestLogApplicationEvent> {

    private final GatewayRequestLogReactiveElasticsearchRepository repository;

    public GatewayRequestLogApplicationListener(GatewayRequestLogReactiveElasticsearchRepository repository) {
        this.repository = repository;
    }

    @Override
    public void onApplicationEvent(GatewayRequestLogApplicationEvent event) {
        GatewayLog gatewayLog = event.getGatewayLog();
        ActionEnum actionEnum = event.getActionEnum();
        String action = actionEnum.getMsg();
        log.info("开始{}日志入库", action);
        Mono<GatewayLog> logMono = repository.save(gatewayLog);
        logMono
                .doOnSuccess(s -> log.info("{}日志,id为[{}],入库成功:{}", action, gatewayLog.getId(), JSON.toJSONString(s)))
                .doOnError(e -> log.error("{}日志,id为[{}],入库异常:", action, gatewayLog.getId(), e))
                .subscribe();
        log.info("结束{}日志入库", action);
    }
}
