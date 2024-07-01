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
import java.time.format.DateTimeFormatter;
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

       Optional <ChatRoom> chatRoomOptional= chatRoomRepository.findById(chatMessageDTO.getChatRoomId());
            if(chatRoomOptional.isPresent()){
             ChatRoom chatRoom = chatRoomOptional.get();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
                ChatMessage chatMessage = ChatMessage.builder()
                        .chatRoom(chatRoom)
                        .text(chatMessageDTO.getText())
                        .senderId(chatMessageDTO.getSenderId())
                        .receiverId(chatMessageDTO.getReceiverId())
                        .read(false)
                        .createDate(LocalDateTime.now().format(formatter))
                        .updateDate(LocalDateTime.now().format(formatter))
                        .build();

                        ChatMessage getChatMessage= chatMessageRepository.save(chatMessage);
                        return ChatMessageMapper.mapEntityToDto(getChatMessage);
              }
            return null;
    }
}
