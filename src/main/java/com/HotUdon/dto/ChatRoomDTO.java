package com.HotUdon.dto;


import com.HotUdon.model.Member;
import com.HotUdon.model.Register;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChatRoomDTO {


    private Long id;

    private Long registerId;  // 이 채팅방의 판매자 (상품 등록자)

    private Long memberId; //  채팅방의 구매자

    private boolean sellerLeft;

    private boolean buyerLeft;

    private List<ChatMessageDTO> messages;

    private long unreadMessageCount;
}
