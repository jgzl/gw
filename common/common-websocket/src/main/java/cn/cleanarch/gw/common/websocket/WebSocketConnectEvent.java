package cn.cleanarch.gw.common.websocket;

import org.springframework.context.ApplicationEvent;

/**
 * @author lihaifeng
 */
public class WebSocketConnectEvent extends ApplicationEvent {
	public WebSocketConnectEvent(WebSocket webSocket) {
		super(webSocket);
	}
}
