package com.github.gw.gateway.log.listener;

import com.github.gw.gateway.common.ActionEnum;
import com.github.gw.common.gateway.domain.GatewayLog;
import org.springframework.context.ApplicationEvent;

/**
 * @author li7hai26@gmail.com
 * @date 2021/12/17
 */
public class GatewayRequestLogApplicationEvent extends ApplicationEvent {

    private GatewayLog gatewayLog;
    private ActionEnum actionEnum;

    public GatewayRequestLogApplicationEvent(Object source, GatewayLog event, ActionEnum actionEnum) {
        super(source);
        this.gatewayLog = event;
        this.actionEnum = actionEnum;
    }

    public GatewayLog getGatewayLog() {
        return this.gatewayLog;
    }

    public ActionEnum getActionEnum() {
        return actionEnum;
    }
}
