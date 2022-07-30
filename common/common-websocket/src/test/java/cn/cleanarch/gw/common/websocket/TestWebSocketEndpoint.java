package cn.cleanarch.gw.message.websocket;

import cn.cleanarch.gw.common.websocket.BaseWebSocketEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@Slf4j
@Component
@ServerEndpoint(value = "/ws/test/{identifier}")
public class TestWebSocketEndpoint extends BaseWebSocketEndpoint {

    @OnOpen
    @Override
    public void connect(@PathParam(value = "identifier") String identifier, Session session) {
        super.connect(identifier, session);
    }

    @OnClose
    @Override
    public void disconnect(@PathParam(value = "identifier") String identifier) {
        super.disconnect(identifier);
    }

    @OnMessage
    @Override
    public void receiveMessage(@PathParam(value = "identifier") String identifier,String message,Session session) {
        super.receiveMessage(identifier, message, session);
    }


    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(@PathParam(value = "identifier") String identifier,Session session, Throwable error) {
        log.error("webSocket发生错误！identifier：{}", identifier);
        error.printStackTrace();
    }
}
