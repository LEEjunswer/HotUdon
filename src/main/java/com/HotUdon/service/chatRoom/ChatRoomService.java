package com.HotUdon.service.chatRoom;

import com.HotUdon.model.ChatRoom;

public interface ChatRoomService {
    ChatRoom createChatRoom(Long memberId, Long registerId);
}
