package com.github.gw.gateway.decorator;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.github.gw.gateway.common.ActionEnum;
import com.github.gw.common.gateway.domain.GatewayLog;
import com.github.gw.gateway.common.WebEnum;
import com.github.gw.gateway.util.LogUtils;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
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

    private GatewayLog gatewayLog;

    public PartnerServerHttpResponseDecorator(ServerHttpResponse response, GatewayLog gatewayLog) {
        super(response);
        this.gatewayLog = gatewayLog;
        long costTime = DateUtil.between(DateUtil.date(gatewayLog.getCreateTime()), DateUtil.date(LocalDateTime.now()), DateUnit.MS);
        LocalDateTime updateTime = LocalDateTime.now();
        gatewayLog.setExecuteTime(costTime);
        gatewayLog.setUpdateTime(updateTime);
        gatewayLog.setHttpStatus(response.getStatusCode() != null ? response.getStatusCode().value() + "" : null);
    }

    @Override
    public Mono<Void> writeAndFlushWith(Publisher<? extends Publisher<? extends DataBuffer>> body) {
        return super.writeAndFlushWith(body);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {

        final MediaType contentType = super.getHeaders().getContentType();
        if (LogUtils.legalLogMediaTypes.contains(contentType)) {
            if (body instanceof Mono) {
                final Mono<DataBuffer> monoBody = (Mono<DataBuffer>) body;
                return super.writeWith(monoBody.publishOn(single())
                        .map(dataBuffer -> LogUtils.logging(gatewayLog, dataBuffer, ActionEnum.CREATE, WebEnum.RESPONSE)));
            } else if (body instanceof Flux) {
                final Flux<DataBuffer> monoBody = (Flux<DataBuffer>) body;
                return super.writeWith(monoBody.publishOn(single())
                        .map(dataBuffer -> LogUtils.logging(gatewayLog, dataBuffer, ActionEnum.CREATE, WebEnum.RESPONSE)));
            }
        } else {
            if (log.isDebugEnabled()) {
                log.debug("网关只记录非xml,json格式的请求内容,当前请求的Content-Type为{}",contentType);
            }
            LogUtils.logging(gatewayLog,ActionEnum.CREATE);
        }
        if (log.isDebugEnabled()) {
            log.debug("结束访问[{}],合计共消耗时间为:{}ms", gatewayLog.getRequestPath(), gatewayLog.getExecuteTime());
        }
        return super.writeWith(body);
    }
}