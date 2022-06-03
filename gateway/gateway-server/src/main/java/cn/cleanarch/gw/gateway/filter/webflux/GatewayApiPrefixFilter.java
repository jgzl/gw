package cn.cleanarch.gw.gateway.filter.webflux;

import cn.cleanarch.gw.common.core.constant.CommonConstants;
import cn.cleanarch.gw.common.core.constant.GatewayConstants;
import cn.cleanarch.gw.common.core.exception.enums.GlobalErrorCodeConstants;
import cn.cleanarch.gw.common.core.utils.WebfluxUtil;
import cn.cleanarch.gw.gateway.configuration.properties.GatewayProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.addOriginalRequestUrl;

/**
 * 网关API前缀过滤器
 *
 * @author li7hai26@gmail.com
 * @date 2022/6/3
 */
@Slf4j
@RequiredArgsConstructor
public class GatewayApiPrefixFilter implements WebFilter {

    public static final String FILTER_NAME = "gatewayApiPrefixFilter";

    private final GatewayProperties gatewayProperties;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        URI uri = request.getURI();
        addOriginalRequestUrl(exchange, uri);
        String rawPath = uri.getRawPath();
        String apiPrefix = gatewayProperties.getApiPrefix();
        if (rawPath.startsWith(apiPrefix)) {
            String newPath = rawPath.replace(apiPrefix,"/");
            ServerHttpRequest newRequest = request.mutate().path(newPath).build();
            exchange.getAttributes().put(GATEWAY_REQUEST_URL_ATTR, newRequest.getURI());
            return chain.filter(exchange.mutate().request(newRequest).build());
        } else if (rawPath.startsWith(CommonConstants.HEART_BEAT_URL)
                || rawPath.startsWith(CommonConstants.ACTUATOR_URL)
                || rawPath.startsWith(CommonConstants.FAVICON_ICO_URL)
                || rawPath.startsWith(GatewayConstants.ADMIN_PREFIX)
        ) {
            return chain.filter(exchange);
        } else {
            return WebfluxUtil.errorOut(response, GlobalErrorCodeConstants.NOT_FOUND);
        }
    }
}
