package cn.cleanarch.gw.gateway.configuration;

import cn.cleanarch.gw.gateway.common.FilterOrderConstants;
import cn.cleanarch.gw.gateway.configuration.properties.GatewayProperties;
import cn.cleanarch.gw.gateway.filter.webflux.FileSizeFilter;
import cn.cleanarch.gw.gateway.filter.webflux.GatewayApiAccessFilter;
import cn.cleanarch.gw.gateway.filter.webflux.GatewayApiLogFilter;
import cn.cleanarch.gw.gateway.filter.webflux.GatewayApiPrefixFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * @author li7hai26@gmail.com
 * @date 2021/12/17
 */
@Configuration
@EnableConfigurationProperties(value = {
        GatewayProperties.class
})
@RequiredArgsConstructor
public class WebfluxConfiguration implements WebFluxConfigurer {

    private final GatewayProperties gatewayProperties;

    @Bean(FileSizeFilter.FILTER_NAME)
    public FileSizeFilter fileSizeFilter() {
        return new FileSizeFilter(gatewayProperties.getFile().getFileMaxSize());
    }

    @Order(FilterOrderConstants.GATEWAY_API_ACCESS_FILTER)
    @Bean(GatewayApiAccessFilter.FILTER_NAME)
    public GatewayApiAccessFilter gatewayApiAccessFilter() {
        return new GatewayApiAccessFilter();
    }

    @Order(FilterOrderConstants.GATEWAY_API_PREFIX_FILTER)
    @Bean(GatewayApiPrefixFilter.FILTER_NAME)
    public GatewayApiPrefixFilter gatewayApiPrefixFilter() {
        return new GatewayApiPrefixFilter(gatewayProperties);
    }

    @Order(FilterOrderConstants.GATEWAY_API_LOG_FILTER)
    @Bean(GatewayApiLogFilter.FILTER_NAME)
    public GatewayApiLogFilter gatewayApiLogFilter() {
        return new GatewayApiLogFilter(gatewayProperties);
    }
}
