package cn.cleanarch.gw.common.websocket.redis.action;

import cn.hutool.json.JSONObject;
import cn.cleanarch.gw.common.websocket.WebSocket;
import cn.cleanarch.gw.common.websocket.WebSocketManager;
import cn.cleanarch.gw.common.websocket.utils.WebSocketUtil;

/**
 * {
 * "action":"sendMessage",
 * "identifier":"xxx",
 * "message":"xxxxxxxxxxx"
 * }
 * 给webSocket发送消息的action
 *
 * @author lihaifeng
 */
public class SendMessageAction implements Action {
	@Override
	public void doMessage(WebSocketManager manager, JSONObject object) {
		if (!object.containsKey(IDENTIFIER)) {
			return;
		}
		if (!object.containsKey(MESSAGE)) {
			return;
		}

		String identifier = object.getStr(IDENTIFIER);

		WebSocket webSocket = manager.get(identifier);
		if (null == webSocket) {
			return;
		}
		WebSocketUtil.sendMessage(webSocket.getSession(), object.getStr(MESSAGE));
	}
}
