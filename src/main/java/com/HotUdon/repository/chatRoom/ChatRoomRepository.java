package com.HotUdon.repository.chatRoom;

import com.HotUdon.model.ChatRoom;
import com.HotUdon.model.Member;
import com.HotUdon.model.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom,Long>, ChatRoomCustomRepository{
    Optional<ChatRoom> findByMemberAndRegister(Member member, Register register);

    @Query("SELECT c FROM ChatRoom  c where (c.register =:register AND (c.register.member =:member or c.member =:member))")
    Optional<ChatRoom> findBySellerAndBuyerAndRegister(@Param("member") Member member, @Param("register") Register register);
}
