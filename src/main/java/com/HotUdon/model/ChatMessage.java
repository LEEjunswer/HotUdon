    package com.HotUdon.model;


    import lombok.*;
    import org.hibernate.annotations.Comment;

    import jakarta.persistence.*;

    @Entity
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public class ChatMessage {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "message_id", nullable = false)
        private Long id;

        @Comment("구매자와 판매자 채팅 대화내용")
        private String text;

        @Comment("대화 생성 날짜")
        @Column(updatable = false)
        private String createDate;

        @Comment("대화 업데이트 날짜")
        private String updateDate;

        @Comment("채팅을 읽었는지 안읽었는지 체크하기 구매자")
        @Column(nullable = false)
        private boolean senderRead;

        @Comment("채팅을 읽었는지 안읽었는지 체크하기 판매자")
        @Column(nullable = false)
        private boolean receiverRead;
    
        @Comment("구매자가 발신을 했는가")
        private Long senderId;
        
        @Comment("판매자가 발신을 했는가")
        private Long receiverId;


        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "chat_room_id", nullable = false)
        private ChatRoom chatRoom;  // 해당 메시지가 속한 채팅방 참조
    }