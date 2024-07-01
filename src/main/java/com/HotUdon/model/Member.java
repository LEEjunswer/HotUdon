package com.HotUdon.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Comment;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Getter
@Setter
@Entity
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("member Pk")
    @Column(name = "member_id",nullable = false)
    private Long id;

    @Comment("로그인 ID")
    @Column(unique = true, updatable = false)
    private String loginId;

    @Comment("유저패스워드")
    private String password;
    
    @Comment("유저 닉네임 중복 불가")  // 될 수 있으면 닉네임으로도 로그인 줄 예정
    @Column(unique = true)
    private String nickName;
    
    @Comment("유저 핸드폰 번호 중복 불가")
    @Column(unique = true)
    private String phone;

    private String email;

    @Comment("우편번호")
    private int postCode;
    @Comment("유저 주소")
    private String address;
    @Comment("유저 주소 디테일")
    private String addressDetail;


    @Comment("유저 회원가입일")
    @Column(updatable = false)
    private  String regDate;

    @Comment("유저 로그인할떄마다 업데이트")
    private String loginDay;

    @Comment("유저 포인트")
    private int point;

    @Comment("유저휴면계정유무")
    private Boolean dormantAccount;

    @Comment("고객 유저 상태 0: 정상 1:정지 2:영구정지") // Enum or String or int 고민중
    private int Status;
    @Comment("고객 보유 금액")
    private int cash;

    @Comment("Role") // 유저인가 관리자인가 매니저인가
    @Enumerated(EnumType.STRING)
    private Role role;

    @Comment("고객 등급") // Enum or String or int 고민중
    private int grade;

    @Comment("멤버 프로필이미지")
    private String profileImg;

    @PrePersist
    protected void onCreate() {
        LocalDateTime dateTime = LocalDateTime.now();
        this.regDate = dateTime.format(DateTimeFormatter.ISO_DATE_TIME);
    }


}
