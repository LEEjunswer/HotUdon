package com.HotUdon.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NotificationDTO {

    private Long id;

    private String content;

    private boolean isDibs;

    private String regDate;

    private Long memberId; //유저에게 알람
 
    private Long registerId; //채팅룸이나 메시지가 생성됐을 떄 시작
}
