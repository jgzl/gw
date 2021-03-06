package cn.cleanarch.gw.gateway.decorator;

import cn.cleanarch.gw.common.gateway.domain.GatewayLog;
import cn.cleanarch.gw.gateway.common.WebEnum;
import cn.cleanarch.gw.gateway.util.LogUtils;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static reactor.core.scheduler.Schedulers.single;

/**
 * @author li7hai26@gmail.com
 * @date 2021/12/17
 */
@Slf4j
public class PartnerServerHttpResponseDecorator extends ServerHttpResponseDecorator {

    private final GatewayLog gatewayLog;
    private final ServerHttpResponse response;
    private final ServerWebExchange delegate;

    public PartnerServerHttpResponseDecorator(ServerWebExchange delegate,ServerHttpResponse response, GatewayLog gatewayLog) {
        super(response);
        this.gatewayLog = gatewayLog;
        this.response = response;
        this.delegate = delegate;
    }

    @Override
    public Mono<Void> writeAndFlushWith(Publisher<? extends Publisher<? extends DataBuffer>> body) {
        return super.writeAndFlushWith(body);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
        LocalDateTime updateTime = LocalDateTime.now();
        long costTime = DateUtil.between(DateUtil.date(gatewayLog.getRequestTime()), DateUtil.date(updateTime), DateUnit.MS);
        gatewayLog.setTargetService(getRoute(delegate).getId());
        gatewayLog.setExecuteTime(costTime);
        gatewayLog.setResponseTime(updateTime);
        gatewayLog.setHttpStatus(response.getStatusCode() != null ? response.getStatusCode().value() + "" : null);
        final MediaType contentType = super.getHeaders().getContentType();
        if (LogUtils.legalLogMediaTypes.contains(contentType)) {
            if (body instanceof Mono) {
                final Mono<DataBuffer> monoBody = (Mono<DataBuffer>) body;
                return super.writeWith(monoBody.publishOn(single())
                        .map(dataBuffer -> LogUtils.logging(gatewayLog, dataBuffer, WebEnum.RESPONSE)));
            } else if (body instanceof Flux) {
                final Flux<DataBuffer> monoBody = (Flux<DataBuffer>) body;
                return super.writeWith(monoBody.publishOn(single())
                        .map(dataBuffer -> LogUtils.logging(gatewayLog, dataBuffer, WebEnum.RESPONSE)));
            }
        } else {
            if (log.isDebugEnabled()) {
                log.debug("??????????????????xml,json?????????????????????,???????????????Content-Type???{}", contentType);
            }
            LogUtils.logging(gatewayLog);
        }
        log.info("????????????[{}],????????????????????????:{}ms", gatewayLog.getRequestPath(), gatewayLog.getExecuteTime());
        return super.writeWith(body);
    }

    private Route getRoute(ServerWebExchange exchange) {
        return exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
    }
}