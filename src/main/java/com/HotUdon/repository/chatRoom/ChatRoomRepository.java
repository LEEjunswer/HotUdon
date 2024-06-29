package com.HotUdon.repository.chatRoom;

import com.HotUdon.model.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom,Long>, ChatRoomCustomRepository{
}
