package cn.cleanarch.gw.message.configuration.websocket;

import org.springframework.context.ApplicationEvent;

/**
 * @author lihaifeng
 */
public class WebSocketConnectEvent extends ApplicationEvent {
	public WebSocketConnectEvent(WebSocket webSocket) {
		super(webSocket);
	}
}
