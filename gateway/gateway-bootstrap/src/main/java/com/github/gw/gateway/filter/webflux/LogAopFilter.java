package com.github.gw.gateway.filter.webflux;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.github.gw.common.core.utils.WebfluxUtil;
import com.github.gw.common.gateway.domain.GatewayLog;
import com.github.gw.gateway.common.GatewayConstants;
import com.github.gw.gateway.configuration.properties.GatewayProperties;
import com.github.gw.gateway.decorator.PayloadServerWebExchangeDecorator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 日志环绕过滤器,使用webflux的filter统一过滤日志请求，网关filter同样需要经过此filter链路
 *
 * @author li7hai26@gmail.com
 * @date 2021/12/16
 */
@Slf4j
@RequiredArgsConstructor
public class LogAopFilter implements WebFilter {

    public static final String FILTER_NAME = "logAopFilter";

    private static final String GATEWAY_START_LOG_TIME = "GATEWAY_START_LOG_TIME";

    private static final AntPathMatcher matcher = new AntPathMatcher();

    private final GatewayProperties properties;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        URI uri = request.getURI();
        String path = uri.getPath();
        String query = uri.getQuery();
        String pathAndQuery = StrUtil.isBlank(query) ? path : path + "?" + query;
        HttpHeaders httpHeaders = request.getHeaders();
        String fastUUID = IdUtil.fastUUID();
        String methodValue = Optional.ofNullable(request.getMethod()).orElse(HttpMethod.GET).name();
        String jsonHeader = JSON.toJSONString(httpHeaders);
        String env = WebfluxUtil.getParameterByHeaderOrPath(request, GatewayConstants.X_BUSINESS_ENV);
        String apiKey = WebfluxUtil.getParameterByHeaderOrPath(request, GatewayConstants.X_BUSINESS_API_KEY);
        String apiSecret = WebfluxUtil.getParameterByHeaderOrPath(request, GatewayConstants.X_BUSINESS_API_SECRET);
        String system = WebfluxUtil.getParameterByHeaderOrPath(request, GatewayConstants.X_BUSINESS_API_SYSTEM);

        log.info("开始访问[{}]", path);
        List<String> withOutTracePaths = properties.getPath().getWithOutTracePaths();
        if (CollUtil.isNotEmpty(withOutTracePaths)) {
            for (String withOutTracePath : withOutTracePaths) {
                if (log.isDebugEnabled()) {
                    log.debug("系统配置不记录日志的路径为:{}", withOutTracePath);
                }
                if (matcher.match(withOutTracePath, path)) {
                    // 如果不记录日志路径和当前请求路径匹配则不记录日志
                    Map<String, Object> attributes = exchange.getAttributes();
                    long start = Instant.now().toEpochMilli();
                    attributes.put(GATEWAY_START_LOG_TIME, start);
                    return chain.filter(exchange).then(
                            Mono.fromRunnable(() -> {
                                long startTime = exchange.getAttribute(GATEWAY_START_LOG_TIME);
                                long endTime = (Instant.now().toEpochMilli() - startTime);
                                log.info("结束访问[{}],共消耗时间为:{}ms", exchange.getRequest().getURI().getRawPath(), endTime);
                            })
                    );
                }
            }
        }

        if (log.isDebugEnabled()) {
            log.debug("访问[{}]header的json格式为:[{}]", path, jsonHeader);
        }

        GatewayLog gatewayLog = new GatewayLog();
        // 先保存再更新以防无法接受到相应请求
        gatewayLog.setId(fastUUID);
        gatewayLog.setRequestHeader(jsonHeader);
        gatewayLog.setRequestPath(path);
        gatewayLog.setRequestPathAndQuery(pathAndQuery);
        gatewayLog.setRequestMethod(methodValue);
        gatewayLog.setCreateTime(LocalDateTime.now());
        gatewayLog.setEnvironment(env);
        gatewayLog.setApiKey(apiKey);
        gatewayLog.setApiSecret(apiSecret);
        gatewayLog.setSystem(system);
        return chain.filter(new PayloadServerWebExchangeDecorator(exchange, gatewayLog));
    }
}
