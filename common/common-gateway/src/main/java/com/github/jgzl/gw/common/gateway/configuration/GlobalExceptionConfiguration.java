package com.github.jgzl.gw.common.gateway.configuration;

import com.github.jgzl.gw.common.core.utils.WebfluxUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author li7hai26@gmail.com
 * @date 2021/5/23
 * <p>
 * 网关异常通用处理器，只作用在webflux 环境下 , 优先级低于 {@link org.springframework.web.server.handler.ResponseStatusExceptionHandler} 执行
 */
@Slf4j
@Order(-1)
@Configuration
@RequiredArgsConstructor
public class GlobalExceptionConfiguration implements ErrorWebExceptionHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();

        if (response.isCommitted()) {
            return Mono.error(ex);
        }

        String msg;

        if (ex instanceof NotFoundException) {
            msg = "未找到服务";
        } else if (ex instanceof ResponseStatusException) {
            ResponseStatusException responseStatusException = (ResponseStatusException) ex;
            msg = responseStatusException.getMessage();
            response.setStatusCode(responseStatusException.getStatus());
        } else {
            msg = "内部服务器错误";
        }

        log.error("[网关异常处理]请求路径:[" + exchange.getRequest().getPath() + "],异常信息:", ex);

        return WebfluxUtil.webFluxResponseWriter(response, msg);
    }

}