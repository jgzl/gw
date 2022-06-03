package cn.cleanarch.gw.gateway.filter.webflux;

import cn.cleanarch.gw.common.core.constant.GatewayConstants;
import cn.cleanarch.gw.common.core.constant.enums.StatusEnum;
import cn.cleanarch.gw.common.core.exception.enums.ErrorCodeConstants;
import cn.cleanarch.gw.common.core.utils.WebfluxUtil;
import cn.cleanarch.gw.common.gateway.support.AccessConfCacheHolder;
import cn.cleanarch.gw.common.model.gateway.vo.GatewayAccessConfVo;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
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
public class GatewayApiAccessFilter implements WebFilter {

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
            ServerHttpResponse response = exchange.getResponse();
            if (vo == null) {
                return WebfluxUtil.errorOut(response, ErrorCodeConstants.GATEWAY_ACCESS_API_KEY_NOT_EXIST);
            }
            if (!apiSecret.equals(vo.getApiSecret())) {
                return WebfluxUtil.errorOut(response, ErrorCodeConstants.GATEWAY_ACCESS_API_SECRET_NOT_VALID);
            }
            if (StatusEnum.DISABLE.getCode().equals(vo.getStatus())) {
                return WebfluxUtil.errorOut(response, ErrorCodeConstants.GATEWAY_ACCESS_DISABLED);
            }
        }
        return chain.filter(exchange);
    }
}
