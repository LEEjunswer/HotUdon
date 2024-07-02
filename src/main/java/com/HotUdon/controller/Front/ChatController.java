package com.HotUdon.controller.Front;

import com.HotUdon.dto.ChatMessageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessageSendingOperations messagingTemplate;
    @MessageMapping("/chat/{roomId}/message")
    public void sendMessage(ChatMessageDTO chatMessage, @DestinationVariable String roomId) {
        System.out.println("Sending message to room " + roomId + ": " + chatMessage.getText());
        messagingTemplate.convertAndSend("/topic/chat/" + roomId, chatMessage);

    }

}
