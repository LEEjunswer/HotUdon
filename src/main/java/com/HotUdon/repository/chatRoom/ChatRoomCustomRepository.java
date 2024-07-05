package com.HotUdon.repository.chatRoom;

import com.HotUdon.model.ChatRoom;

import java.util.List;

public interface ChatRoomCustomRepository {
    List<ChatRoom> findAllByMemberId(Long memberId);
  /*  int findAllByMemberIdCount(Long memberId);*/
/*    int findAllByRegisterIdInMemberIdCount(Long memberId);*/
    List<ChatRoom> findByAllMessageMemberId(Long memberId); //유저에 관련된 구매메시지 판매메시지 전부 들고오기
}
