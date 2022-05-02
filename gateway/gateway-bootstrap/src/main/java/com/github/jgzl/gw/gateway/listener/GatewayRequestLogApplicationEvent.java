package com.github.jgzl.gw.gateway.listener;

import com.github.jgzl.gw.common.gateway.domain.GatewayLog;
import org.springframework.context.ApplicationEvent;

/**
 * @author li7hai26@gmail.com
 * @date 2021/12/17
 */
public class GatewayRequestLogApplicationEvent extends ApplicationEvent {

    private final GatewayLog gatewayLog;

    public GatewayRequestLogApplicationEvent(Object source, GatewayLog event) {
        super(source);
        this.gatewayLog = event;
    }

    public GatewayLog getGatewayLog() {
        return this.gatewayLog;
    }

}
