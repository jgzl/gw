package com.github.gw.gateway.configuration;

import com.github.gw.gateway.configuration.properties.GatewayProperties;
import com.github.gw.gateway.filter.webflux.AccessFilter;
import com.github.gw.gateway.filter.webflux.FileSizeFilter;
import com.github.gw.gateway.filter.webflux.RecordLogFilter;
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
    @Order
    public FileSizeFilter fileSizeFilter() {
        return new FileSizeFilter(gatewayProperties.getFile().getFileMaxSize());
    }

    @Bean(RecordLogFilter.FILTER_NAME)
    @Order
    public RecordLogFilter recordLogFilter() {
        return new RecordLogFilter(gatewayProperties);
    }

    @Bean(AccessFilter.FILTER_NAME)
    @Order
    public AccessFilter accessFilter() {
        return new AccessFilter();
    }
}
