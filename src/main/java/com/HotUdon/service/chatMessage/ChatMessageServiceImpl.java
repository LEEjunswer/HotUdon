package com.HotUdon.service.chatMessage;

import com.HotUdon.model.ChatMessage;
import com.HotUdon.model.ChatRoom;
import com.HotUdon.repository.chatMessage.ChatMessageRepository;
import com.HotUdon.repository.chatRoom.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService{

    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;

    @Override
    public ChatMessage sendMessage(Long chatRoomId, String text) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid chat room ID"));

        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setText(text);
        chatMessage.setCreateDate(LocalDateTime.now().toString());
        chatMessage.setRead(false);
        chatMessage.setChatRoom(chatRoom);
        return chatMessageRepository.save(chatMessage);
    }
}
