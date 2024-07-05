package com.HotUdon.service.chatRoom;

import com.HotUdon.dto.ChatRoomDTO;
import com.HotUdon.model.ChatRoom;

import java.util.List;

public interface ChatRoomService {
    ChatRoomDTO createChatRoom(Long memberId, Long registerId);
    List<ChatRoomDTO> findAllByMemberId(Long memberId);
    ChatRoomDTO findById(Long roomId);
 /*여기 손보기*/
    /*   int findByUnreadCount(Long memberId);*/
}
