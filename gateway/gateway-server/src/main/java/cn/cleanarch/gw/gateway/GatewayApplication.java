package cn.cleanarch.gw.gateway;

import cn.cleanarch.gw.common.feign.EnableFeignClientsX;
import cn.cleanarch.gw.common.gateway.annotation.EnableDynamicRoute;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;

import java.util.stream.Collectors;

/**
 * @author li7hai26@gmail.com
 * @date 2021/10/8
 */
@EnableFeignClientsX
@EnableDynamicRoute
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}