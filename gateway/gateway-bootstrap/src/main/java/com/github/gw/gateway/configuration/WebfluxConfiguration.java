package com.github.gw.gateway.configuration;

import com.github.gw.gateway.configuration.properties.GatewayProperties;
import com.github.gw.gateway.filter.webflux.WhitePathFilter;
import com.github.gw.gateway.filter.webflux.FileSizeFilter;
import com.github.gw.gateway.filter.webflux.LogAopFilter;
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
public class WebfluxConfiguration implements WebFluxConfigurer {

    @Bean
    @Order
    public FileSizeFilter fileSizeFilter(GatewayProperties gatewayProperties) {
        return new FileSizeFilter(gatewayProperties.getFile().getFileMaxSize());
    }

    @Bean
    @Order
    public WhitePathFilter excludePathFilter(GatewayProperties gatewayProperties) {
        return new WhitePathFilter(gatewayProperties);
    }

    @Bean
    @Order
    public LogAopFilter logAopFilter(GatewayProperties gatewayProperties) {
        return new LogAopFilter(gatewayProperties);
    }

    @Override
    public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
        configurer.defaultCodecs().maxInMemorySize(20 * 1024 * 1024);
    }
}
