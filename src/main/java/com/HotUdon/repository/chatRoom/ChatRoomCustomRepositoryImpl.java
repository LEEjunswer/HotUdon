package com.HotUdon.repository.chatRoom;

import com.HotUdon.model.ChatRoom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.HotUdon.model.QChatRoom.chatRoom;
@Repository
@RequiredArgsConstructor
public class ChatRoomCustomRepositoryImpl implements ChatRoomCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<ChatRoom> findAllByMemberId(Long memberId) {

        return  jpaQueryFactory.
                selectFrom(chatRoom)
                .where(chatRoom.member.id.eq(memberId))
                .fetch();
    }
}
