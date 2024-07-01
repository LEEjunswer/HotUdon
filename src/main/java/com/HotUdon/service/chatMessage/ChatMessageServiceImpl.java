package com.HotUdon.service.chatMessage;

import com.HotUdon.dto.ChatMessageDTO;
import com.HotUdon.mapper.ChatMessageMapper;
import com.HotUdon.model.ChatMessage;
import com.HotUdon.model.ChatRoom;
import com.HotUdon.repository.chatMessage.ChatMessageRepository;
import com.HotUdon.repository.chatRoom.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService{

    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;



    @Override
    public ChatMessageDTO sendMessage(ChatMessageDTO chatMessageDTO) {
            if(chatMessageDTO == null){
                return  null;
            }
       Optional <ChatRoom> chatRoom= chatRoomRepository.findById(chatMessageDTO.getChatRoomId());
            if(chatRoom.isPresent()){
                ChatMessage chatMessage = ChatMessage.builder()
                        .chatRoom(chatRoom.get())
                        .text(chatMessageDTO.getText())
                        .read(false).updateDate(new Date().toString())
                        .build();
                        ChatMessage getChatMessage= chatMessageRepository.save(chatMessage);
                        return ChatMessageMapper.mapEntityToDto(getChatMessage);
              }
            return null;
    }
}
