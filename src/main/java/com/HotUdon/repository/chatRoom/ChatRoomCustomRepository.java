package com.HotUdon.repository.chatRoom;

import com.HotUdon.model.ChatRoom;

import java.util.List;

public interface ChatRoomCustomRepository {
    List<ChatRoom> findAllByMemberId(Long memberId);
}
