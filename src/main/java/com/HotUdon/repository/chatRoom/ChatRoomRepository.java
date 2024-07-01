package com.HotUdon.repository.chatRoom;

import com.HotUdon.model.ChatRoom;
import com.HotUdon.model.Member;
import com.HotUdon.model.Register;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom,Long>, ChatRoomCustomRepository{
    Optional<ChatRoom> findByMemberAndRegister(Member member, Register register);
}
