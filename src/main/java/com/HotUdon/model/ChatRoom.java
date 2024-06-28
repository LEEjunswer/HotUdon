package com.HotUdon.model;


import lombok.*;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "register_id", nullable = false)
    private Register register;  // 이 채팅방의 판매자 (상품 등록자)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member; //  채팅방의 구매자

    @Column(name = "seller_left", nullable = false)
    private boolean sellerLeft = false; // 판매자가 나갔는지 여부

    @Column(name = "buyer_left", nullable = false)
    private boolean buyerLeft = false; // 구매자가 나갔는지 여부

    @OneToMany(mappedBy = "chatRoom")
    private List<ChatMessage> messages = new ArrayList<>();


    @Transient // 데이터 저장X 메시지를 전달 했을떄 읽었는지 안읽었는지 체크하기
    public long getUnreadMessageCountALong() {
        return this.getMessages().stream().filter(message -> !message.isRead()).count();
    }
}
