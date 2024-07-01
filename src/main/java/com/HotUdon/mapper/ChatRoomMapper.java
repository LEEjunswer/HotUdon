package com.HotUdon.mapper;

import com.HotUdon.dto.ChatMessageDTO;
import com.HotUdon.dto.ChatRoomDTO;
import com.HotUdon.model.ChatMessage;
import com.HotUdon.model.ChatRoom;
import com.HotUdon.model.Member;
import com.HotUdon.model.Register;

import java.util.List;
import java.util.stream.Collectors;

public class ChatRoomMapper {

    public static ChatRoomDTO mapEntityToDto(ChatRoom chatRoom){
        if(chatRoom == null){
            return  null;
        }
        ChatRoomDTO chatRoomDTO = new ChatRoomDTO();
        chatRoomDTO.setId(chatRoom.getId());
        chatRoomDTO.setRegister(chatRoom.getRegister());
       chatRoomDTO.setMember(chatRoom.getMember());
        chatRoomDTO.setBuyerLeft(chatRoom.isBuyerLeft());
        chatRoomDTO.setSellerLeft(chatRoom.isSellerLeft());
        chatRoomDTO.setUnreadMessageCount(chatRoom.getUnreadMessageCountALong());
        List<ChatMessageDTO> messageDTOs = chatRoom.getMessages().stream()
                .map(ChatMessageMapper:: mapEntityToDto )
                .collect(Collectors.toList());
        chatRoomDTO.setMessages(messageDTOs);
        return  chatRoomDTO;
    }
    public static ChatRoom mapDtoToEntity(ChatRoomDTO chatRoomDTO, Member member, Register register, List<ChatMessage> chatMessage){
            if(chatRoomDTO == null){
                return  null;
            }
            ChatRoom chatRoom = new ChatRoom();
            chatRoom.setId(chatRoomDTO.getId());
            chatRoom.setMember(member);
            chatRoom.setRegister(register);
            chatRoom.setBuyerLeft(chatRoomDTO.isBuyerLeft());
            chatRoom.setSellerLeft(chatRoomDTO.isSellerLeft());
            chatRoom.setMessages(chatMessage);
            return chatRoom;
    }
}
