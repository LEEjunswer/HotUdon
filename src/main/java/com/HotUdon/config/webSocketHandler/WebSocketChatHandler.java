package com.HotUdon.config.webSocketHandler;

import com.HotUdon.config.WebSocketConfig;
import com.HotUdon.dto.ChatMessageDTO;
import com.HotUdon.model.ChatMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketChatHandler extends TextWebSocketHandler {

    private final ObjectMapper mapper;
    
    //현재 연겯횐 세션들
    private final Set<WebSocketSession> sessions = new HashSet<>();
    
    // chatRoomId : {session1,session2}
    private final Map<Long,Set<WebSocketSession>> chatRoomSessionMap = new HashMap<>();

    // 소켓 연결 확인하는 것
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception{
        // TODO Auto-generated method stub
        log.info("{} 연결됨",session.getId());
        sessions.add(session);
    }
    //소켓 통신 시 메시지의 전송을 다루는 부분
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload {}",payload);
        // 페이로드 -> chatMessageDto로 변환

        ChatMessageDTO chatMessageDTO = mapper.readValue(payload, ChatMessageDTO.class);
        log.info(" session {}", chatMessageDTO.toString());
        Long chatRoomId =chatMessageDTO.getChatRoomId();

        //메모리 상에 채팅방에 대한 세션이 없으면 만들어준다.
        if(!chatRoomSessionMap.containsKey(chatRoomId)){
            chatRoomSessionMap.put(chatRoomId, new HashSet<>());
        }
        Set<WebSocketSession> chatRoomSession =chatRoomSessionMap.get(chatRoomId);

        // message 타입인지 확인
        // message 에서 getType 으로 가져 온 내용
        // chatDto 의 열거형인  messageType 안에 있는 Enter 동일 값이면
        sendMessageToChatRoom(chatMessageDTO, chatRoomSession);
    }
    // 소켓 종료 확인
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // TODO Auto-generated method stub
        log.info("{} 연결 끊김", session.getId());
        sessions.remove(session);
    }

    // ====== 채팅 관련 메소드 ======
    private void removeClosedSession(Set<WebSocketSession> chatRoomSession) {
        chatRoomSession.removeIf(sess -> !sessions.contains(sess));
    }

    private void sendMessageToChatRoom(ChatMessageDTO chatMessageDTO, Set<WebSocketSession> chatRoomSession) {
        chatRoomSession.parallelStream().forEach(sess -> sendMessage(sess, chatMessageDTO));//2
    }


    public <T> void sendMessage(WebSocketSession session, T message) {
        try{
            session.sendMessage(new TextMessage(mapper.writeValueAsString(message)));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
