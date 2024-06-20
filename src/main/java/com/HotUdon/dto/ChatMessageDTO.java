    package com.HotUdon.dto;

    import com.HotUdon.model.ChatRoom;
    import jakarta.persistence.*;
    import lombok.*;
    import org.hibernate.annotations.Comment;


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public class ChatMessageDTO {

        private Long id;

        @Comment("구매자와 판매자 채팅 대화내용")
        private String text;

        @Comment("대화 생성 날짜")
        private String createDate;


        private String updateDate;

        private ChatRoomDTO chatRoomDTO;  // 해당 메시지가 속한 채팅방 참조
    }