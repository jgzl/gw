package com.github.jgzl.gw.gateway.filter.webflux;

import cn.hutool.core.util.StrUtil;
import com.github.jgzl.gw.common.core.constant.enums.StatusEnum;
import com.github.jgzl.gw.common.core.utils.WebfluxUtil;
import com.github.jgzl.gw.common.gateway.support.AccessConfCacheHolder;
import com.github.jgzl.gw.common.model.gateway.vo.GatewayAccessConfVo;
import com.github.jgzl.gw.common.core.constant.GatewayConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * 网关访问过滤器
 *
 * @author li7hai26@gmail.com
 * @date 2021/12/16
 */
@Slf4j
public class AccessFilter implements WebFilter {

    public static final String FILTER_NAME = "accessFilter";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String env = WebfluxUtil.getParameterByHeaderOrPath(request, GatewayConstants.X_BUSINESS_ENV);
        String apiKey = WebfluxUtil.getParameterByHeaderOrPath(request, GatewayConstants.X_BUSINESS_API_KEY);
        String apiSecret = WebfluxUtil.getParameterByHeaderOrPath(request, GatewayConstants.X_BUSINESS_API_SECRET);
        String system = WebfluxUtil.getParameterByHeaderOrPath(request, GatewayConstants.X_BUSINESS_API_SYSTEM);
        if (StrUtil.isNotBlank(apiKey)&&StrUtil.isNotBlank(apiSecret)) {
            GatewayAccessConfVo vo = AccessConfCacheHolder.get(apiKey);
            if (vo == null) {
                return WebfluxUtil.webFluxResponseWriter(exchange.getResponse(),"网关访问key不存在",100001);
            }
            if (!apiSecret.equals(vo.getApiSecret())) {
                return WebfluxUtil.webFluxResponseWriter(exchange.getResponse(),"网关访问secret不合法",100002);
            }
            if (StatusEnum.DISABLE.getCode().equals(vo.getStatus())) {
                return WebfluxUtil.webFluxResponseWriter(exchange.getResponse(),"网关访问key secret已禁用",100003);
            }
        }
        return chain.filter(exchange);
    }
}
