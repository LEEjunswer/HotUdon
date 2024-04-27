package com.HotUdon.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberDTO {

    private Long id;

    private String loginId;

    private String password;

    private String nickName;

    private String phone;

    private String location;

    private  String regDate;

    private String loginDay;

    private int point;

    private Boolean dormantAccount;

    private String Status;

    private int cash;

    private String grade;
}

