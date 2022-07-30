package cn.cleanarch.gw.common.websocket.redis.action;

import cn.hutool.json.JSONObject;
import cn.cleanarch.gw.common.websocket.WebSocket;
import cn.cleanarch.gw.common.websocket.WebSocketManager;

import java.util.Map;

/**
 * {
 * "action":"remove",
 * "identifier":"xxx"
 * }
 * 给webSocket发送消息的action
 *
 * @author lihaifeng
 */
public class RemoveAction implements Action {
	@Override
	public void doMessage(WebSocketManager manager, JSONObject object) {
		if (!object.containsKey(IDENTIFIER)) {
			return;
		}
		String identifier = object.getStr(IDENTIFIER);
		Map<String, WebSocket> localWebSocketMap = manager.localWebSocketMap();
		localWebSocketMap.remove(identifier);
	}
}
