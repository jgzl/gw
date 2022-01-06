package com.github.gw.gateway.decorator;

import com.github.gw.gateway.common.ActionEnum;
import com.github.gw.common.gateway.domain.GatewayLog;
import com.github.gw.gateway.util.LogUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import reactor.core.publisher.Flux;

import static reactor.core.scheduler.Schedulers.single;

/**
 * @author li7hai26@gmail.com
 * @date 2021/12/17
 */
@Slf4j
public class PartnerServerHttpRequestDecorator extends ServerHttpRequestDecorator {

    private Flux<DataBuffer> body;

    PartnerServerHttpRequestDecorator(ServerHttpRequest delegate, GatewayLog saveGatewayLog) {
        super(delegate);
        final MediaType contentType = delegate.getHeaders().getContentType();

        Flux<DataBuffer> flux = super.getBody();
        if (contentType == null || LogUtils.legalLogMediaTypes.contains(contentType)) {
            body = flux.publishOn(single()).map(dataBuffer -> LogUtils.logging(saveGatewayLog, dataBuffer, ActionEnum.CREATE, ActionEnum.REQUEST));
        } else {
            log.info("网关只记录非xml,json格式的请求内容");
            body = flux;
        }
    }

    @Override
    public Flux<DataBuffer> getBody() {
        return body;
    }

}
