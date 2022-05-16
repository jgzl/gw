package com.github.jgzl.gw.gateway.filter.gateway.global;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.github.jgzl.gw.common.core.constant.GatewayConstants;
import com.github.jgzl.gw.common.core.utils.JacksonUtil;
import com.github.jgzl.gw.common.core.utils.WebfluxUtil;
import com.github.jgzl.gw.common.gateway.domain.GatewayLog;
import com.github.jgzl.gw.gateway.configuration.properties.GatewayProperties;
import com.github.jgzl.gw.gateway.decorator.PayloadServerWebExchangeDecorator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 日志环绕过滤器
 *
 * @author li7hai26@gmail.com
 * @date 2021/12/16
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class RecordLogGlobalFilter implements GlobalFilter, Ordered {

    private static final String GATEWAY_START_LOG_TIME = RecordLogGlobalFilter.class.getName() + ".GATEWAY_START_LOG_TIME";

    private static final AntPathMatcher matcher = new AntPathMatcher();

    private final GatewayProperties properties;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        URI uri = request.getURI();
        String path = uri.getPath();
        String query = uri.getQuery();
        String pathAndQuery = StrUtil.isBlank(query) ? path : path + "?" + query;
        HttpHeaders httpHeaders = request.getHeaders();
        String fastUUID = IdUtil.fastUUID();
        String methodValue = Optional.ofNullable(request.getMethod()).orElse(HttpMethod.GET).name();
        String jsonHeader = JacksonUtil.toJsonString(httpHeaders);
        String env = WebfluxUtil.getParameterByHeaderOrPath(request, GatewayConstants.X_BUSINESS_ENV);
        String apiKey = WebfluxUtil.getParameterByHeaderOrPath(request, GatewayConstants.X_BUSINESS_API_KEY);
        String apiSecret = WebfluxUtil.getParameterByHeaderOrPath(request, GatewayConstants.X_BUSINESS_API_SECRET);
        String system = WebfluxUtil.getParameterByHeaderOrPath(request, GatewayConstants.X_BUSINESS_API_SYSTEM);

        List<String> notRecordPathList = properties.getPath().getNotRecordPath();
        if (CollUtil.isNotEmpty(notRecordPathList)) {
            for (String notRecordPath : notRecordPathList) {
                if (log.isDebugEnabled()) {
                    log.debug("系统配置不记录日志的路径为:{}", notRecordPath);
                }
                if (matcher.match(notRecordPath, path)) {
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

        log.info("开始访问[{}]", path);
        if (log.isDebugEnabled()) {
            log.debug("访问[{}]header的json格式为:[{}]", path, jsonHeader);
        }

        GatewayLog gatewayLog = new GatewayLog();
        // 先保存再更新以防无法接受到相应请求
        gatewayLog.setId(fastUUID);
        gatewayLog.setTargetService(getRoute(exchange).getId());
        gatewayLog.setRequestHeader(jsonHeader);
        gatewayLog.setRequestPath(path);
        gatewayLog.setRequestPathAndQuery(pathAndQuery);
        gatewayLog.setRequestMethod(methodValue);
        gatewayLog.setRequestTime(LocalDateTime.now(ZoneId.of("GMT")));
        gatewayLog.setEnvironment(env);
        gatewayLog.setApiKey(apiKey);
        gatewayLog.setApiSecret(apiSecret);
        gatewayLog.setSystem(system);
        return chain.filter(new PayloadServerWebExchangeDecorator(exchange, gatewayLog));
    }

    private Route getRoute(ServerWebExchange exchange) {
        return exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
    }

    @Override
    public int getOrder() {
        return -1001;
    }
}
