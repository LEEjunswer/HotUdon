package com.HotUdon.mapper;

import com.HotUdon.dto.ChatMessageDTO;
import com.HotUdon.dto.ChatRoomDTO;
import com.HotUdon.model.ChatMessage;
import com.HotUdon.model.ChatRoom;

public class ChatMessageMapper {

    public static ChatMessageDTO mapEntityToDto(ChatMessage chatMessage){
        if (chatMessage == null) {
        return  null;
        }
        ChatMessageDTO chatMessageDTO = new ChatMessageDTO();
        chatMessageDTO.setId(chatMessage.getId());
        chatMessageDTO.setText(chatMessage.getText());
        chatMessageDTO.setCreateDate(chatMessage.getCreateDate());
        chatMessageDTO.setUpdateDate(chatMessage.getUpdateDate());
        chatMessageDTO.setChatRoomId(chatMessage.getChatRoom().getId());
        chatMessageDTO.setRead(chatMessage.isRead());
        return chatMessageDTO;
    }
    public static ChatMessage mapDtoToEntity(ChatMessageDTO chatMessageDTO, ChatRoom chatRoom){
        if(chatMessageDTO == null){
            return null;
        }
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setId(chatMessageDTO.getId());
        chatMessage.setText(chatMessageDTO.getText());
        chatMessage.setRead(chatMessageDTO.isRead());
        chatMessage.setCreateDate(chatMessageDTO.getCreateDate());
        chatMessage.setUpdateDate(chatMessageDTO.getUpdateDate());
        chatMessage.setChatRoom(chatRoom);
        return chatMessage;
    }
}
