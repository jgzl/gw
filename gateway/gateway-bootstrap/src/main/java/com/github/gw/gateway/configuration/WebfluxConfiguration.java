package com.github.gw.gateway.configuration;

import com.github.gw.gateway.configuration.properties.GatewayProperties;
import com.github.gw.gateway.filter.webflux.FileSizeFilter;
import com.github.gw.gateway.filter.webflux.LogAopFilter;
import com.github.gw.gateway.filter.webflux.WhitePathFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
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

    @Bean
    @Order
    public FileSizeFilter fileSizeFilter() {
        return new FileSizeFilter(gatewayProperties.getFile().getFileMaxSize());
    }

    @Bean
    @Order
    public WhitePathFilter excludePathFilter() {
        return new WhitePathFilter(gatewayProperties);
    }

    @Bean
    @Order
    public LogAopFilter logAopFilter() {
        return new LogAopFilter(gatewayProperties);
    }

    @Override
    public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
        configurer.defaultCodecs().maxInMemorySize(gatewayProperties.getLimitBodySize());
    }
}