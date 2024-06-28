package com.HotUdon.service.chatMessage;


import com.HotUdon.model.ChatMessage;

public interface ChatMessageService {
    ChatMessage sendMessage(Long chatRoomId,String text);


}
