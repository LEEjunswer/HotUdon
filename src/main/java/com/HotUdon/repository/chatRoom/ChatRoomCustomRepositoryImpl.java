package com.HotUdon.repository.chatRoom;

import com.HotUdon.model.ChatRoom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.HotUdon.model.QChatMessage.chatMessage;
import static com.HotUdon.model.QChatRoom.chatRoom;

@Repository
@RequiredArgsConstructor
public class ChatRoomCustomRepositoryImpl implements ChatRoomCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;


    /*구매자 로직 가지고오기*/
    @Override
    public List<ChatRoom> findAllByMemberId(Long memberId) {
        return  jpaQueryFactory.
                selectFrom(chatRoom)
                .where(chatRoom.member.id.eq(memberId))
                .fetch();
    }


    /*구매자 로직 가지고오기*/
   /* @Override
    public int findAllByMemberIdCount(Long memberId) {
        return jpaQueryFactory
                .select(chatMessage)
                .from(chatRoom)
                .join(chatRoom.messages, chatMessage)
                .where(
                        chatRoom.member.id.eq(memberId)
                                .and(chatMessage.read.isFalse())
                )
                .fetch()
                .size();
    }
*/
/*
    @Override
    public int findAllByRegisterIdInMemberIdCount(Long memberId) {
        return jpaQueryFactory
                .select(chatMessage)
                .from(chatRoom)
                .join(chatRoom.messages, chatMessage)
                .where(chatRoom.register.member.id.eq(memberId)
                        .and(chatMessage.read.isFalse()))
                .fetch()
                .size();
    }
*/

    @Override
    public List<ChatRoom> findByAllMessageMemberId(Long memberId) {
        return jpaQueryFactory
                .selectFrom(chatRoom)
                .leftJoin(chatRoom.messages, chatMessage)
                .where(chatRoom.register.member.id.eq(memberId)
                        .or(chatRoom.member.id.eq(memberId))
                        .and(chatRoom.buyerLeft.isFalse())
                        .and(chatRoom.sellerLeft.isFalse()))
                .orderBy(chatMessage.updateDate.desc())
                .fetch();
    }
}
