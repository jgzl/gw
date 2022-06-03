package cn.cleanarch.gw.gateway.filter.webflux;

import cn.cleanarch.gw.common.core.constant.GatewayConstants;
import cn.cleanarch.gw.common.core.utils.JacksonUtil;
import cn.cleanarch.gw.common.core.utils.WebfluxUtil;
import cn.cleanarch.gw.common.gateway.domain.GatewayLog;
import cn.cleanarch.gw.gateway.configuration.properties.GatewayProperties;
import cn.cleanarch.gw.gateway.decorator.PayloadServerWebExchangeDecorator;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
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
import java.time.ZoneId;
import java.util.Optional;

/**
 * 日志环绕过滤器
 *
 * @author li7hai26@gmail.com
 * @date 2021/12/16
 */
@Slf4j
@RequiredArgsConstructor
public class GatewayApiLogFilter implements WebFilter {

    public static final String FILTER_NAME = "gatewayApiLogFilter";

    private static final String GATEWAY_START_LOG_TIME = GatewayApiLogFilter.class.getName() + ".GATEWAY_START_LOG_TIME";

    private static final AntPathMatcher matcher = new AntPathMatcher();

    private final GatewayProperties gatewayProperties;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        URI uri = request.getURI();
        String rawPath = uri.getRawPath();
        String apiPrefix = gatewayProperties.getApiPrefix();
        log.info("开始访问[{}]", rawPath);

        /**
         * 记录网关统一前缀访问路径访问的日志信息
         */
        if (rawPath.startsWith(apiPrefix)) {
            String query = uri.getQuery();
            String pathAndQuery = StrUtil.isBlank(query) ? rawPath : rawPath + "?" + query;
            HttpHeaders httpHeaders = request.getHeaders();
            String fastUUID = IdUtil.fastUUID();
            String methodValue = Optional.ofNullable(request.getMethod()).orElse(HttpMethod.GET).name();
            String jsonHeader = JacksonUtil.toJsonString(httpHeaders);

            if (log.isDebugEnabled()) {
                log.debug("访问[{}]header的json格式为:[{}]", rawPath, jsonHeader);
            }

            String env = WebfluxUtil.getParameterByHeaderOrPath(request, GatewayConstants.X_BUSINESS_ENV);
            String apiKey = WebfluxUtil.getParameterByHeaderOrPath(request, GatewayConstants.X_BUSINESS_API_KEY);
            String apiSecret = WebfluxUtil.getParameterByHeaderOrPath(request, GatewayConstants.X_BUSINESS_API_SECRET);
            String system = WebfluxUtil.getParameterByHeaderOrPath(request, GatewayConstants.X_BUSINESS_API_SYSTEM);

            GatewayLog gatewayLog = new GatewayLog();
            // 先保存再更新以防无法接受到相应请求
            gatewayLog.setId(fastUUID);
            gatewayLog.setRequestHeader(jsonHeader);
            gatewayLog.setRequestPath(rawPath);
            gatewayLog.setRequestPathAndQuery(pathAndQuery);
            gatewayLog.setRequestMethod(methodValue);
            gatewayLog.setRequestTime(LocalDateTime.now(ZoneId.of("GMT")));
            gatewayLog.setEnvironment(env);
            gatewayLog.setApiKey(apiKey);
            gatewayLog.setApiSecret(apiSecret);
            gatewayLog.setSystem(system);
            return chain.filter(new PayloadServerWebExchangeDecorator(exchange, gatewayLog));
        }

        /**
         * 非网关统一前缀访问路径访问不记录日志信息
         */
        exchange.getAttributes().put(GATEWAY_START_LOG_TIME, Instant.now().toEpochMilli());
        return chain.filter(exchange).then(
                Mono.fromRunnable(() -> {
                    long startTime = exchange.getAttribute(GATEWAY_START_LOG_TIME);
                    long endTime = (Instant.now().toEpochMilli() - startTime);
                    log.info("结束访问[{}],共消耗时间为:{}ms", exchange.getRequest().getURI().getRawPath(), endTime);
                })
        );
    }
}
