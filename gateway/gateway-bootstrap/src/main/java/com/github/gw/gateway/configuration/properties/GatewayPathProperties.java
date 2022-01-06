package com.github.gw.gateway.configuration.properties;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * 路径参数
 *
 * @author li7hai26@gmail.com
 * @date 2021/12/20
 */
@Data
public class GatewayPathProperties {
    /**
     * 网关白名单(访问，限流，降级)
     */
    private List<String> whitePaths = Lists.newArrayList();
    /**
     * 网关黑名单(访问，限流，降级)
     */
    private List<String> blackPaths = Lists.newArrayList();
    /**
     * 网关日志过滤名单(不记录日志路径名单),日志接口不允许通过网关进行调用,套娃调用会产生黑洞影响
     */
    private List<String> withOutTracePaths = Lists.newArrayList("/actuator/**","/gateway/**");
}
