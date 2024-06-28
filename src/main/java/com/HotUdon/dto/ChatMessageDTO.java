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

        private String text;


        private String createDate;


        private String updateDate;

        private boolean read;

        private Long chatRoomId;  // 해당 메시지가 속한 채팅방 참조
    }