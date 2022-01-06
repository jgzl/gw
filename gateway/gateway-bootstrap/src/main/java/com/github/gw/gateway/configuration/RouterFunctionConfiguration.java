package com.github.gw.gateway.configuration;

import com.github.gw.gateway.handler.ImageCodeCheckHandler;
import com.github.gw.gateway.handler.ImageCodeCreateHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

/**
 * @author li7hai26@gmail.com
 * @date 2021/7/5 路由配置信息
 */
@Slf4j
@Configuration
@AllArgsConstructor
public class RouterFunctionConfiguration {

    private final ImageCodeCheckHandler imageCodeCheckHandler;

    private final ImageCodeCreateHandler imageCodeCreateHandler;

    @Bean
    public RouterFunction routerFunction() {
        return RouterFunctions
                .route(RequestPredicates.path("/gateway/code/get").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
                        imageCodeCreateHandler)
                .andRoute(RequestPredicates.POST("/gateway/code/check").and(RequestPredicates.accept(MediaType.ALL)),
                        imageCodeCheckHandler);
    }
}