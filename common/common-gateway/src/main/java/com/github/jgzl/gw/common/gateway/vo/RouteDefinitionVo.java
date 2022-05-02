package com.github.jgzl.gw.common.gateway.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.cloud.gateway.route.RouteDefinition;

import java.io.Serializable;

/**
 * @author li7hai26@gmail.com
 * @date 2021/10/31
 * <p>
 * 扩展此类支持序列化a See RouteDefinition.class
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RouteDefinitionVo extends RouteDefinition implements Serializable {

    /**
     * 路由id
     */
    private String routeId;
    /**
     * 路由名称
     */
    private String routeName;

}
