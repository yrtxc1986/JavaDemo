package idv.wilson.demo;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class SystemWebSocketHandler extends TextWebSocketHandler {

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		session.sendMessage(new TextMessage("echo:" + message.getPayload()));
		super.handleTextMessage(session, message);
	}

}
