package com.github.gw.gateway.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author li7hai26@gmail.com
 * @date 2021/10/8
 */
@EnableTransactionManagement
@EnableDiscoveryClient
@SpringBootApplication(exclude = {ElasticsearchRestClientAutoConfiguration.class})
public class GatewayAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayAdminApplication.class, args);
    }

}