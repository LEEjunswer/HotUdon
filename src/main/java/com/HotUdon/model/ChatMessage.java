    package com.HotUdon.model;

    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import org.hibernate.annotations.Comment;

    @Entity
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class ChatMessage {

        @Id
        @GeneratedValue
        @Column(name = "message_id", nullable = false)
        private Long id;

        @Comment("구매자와 판매자 채팅 대화내용")
        private String text;

        @Comment("대화 생성 날짜")
        @Column(updatable = false)
        private String createDate;

        @Comment("대화 업데이트 날짜")
        private String updateDate;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "room_id", nullable = false)
        private ChatRoom chatRoom;  // 해당 메시지가 속한 채팅방 참조
    }