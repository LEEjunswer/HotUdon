package com.HotUdon.dto;

import com.HotUdon.model.Role;
import jakarta.persistence.Column;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO {

    private Long id;

    private String loginId;

    private String password;

    private String nickName;

    private String phone;

    private int postCode;

    private String address;

    private String addressDetail;

    private String regDate;

    private String loginDay;

    private int point;

    private Boolean dormantAccount;

    private String email;

    private int Status;

    private int cash;

    private Role role;

    private int grade;

    private String profileImg;
}
