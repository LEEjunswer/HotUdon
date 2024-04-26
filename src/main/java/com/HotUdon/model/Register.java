package com.HotUdon.model;


import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.Fetch;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Register {

    @Id
    @Column(name = "regisiter_id" ,nullable = false)
    @GeneratedValue
    @Comment("상품등록 PK")
    private Long Id;

    @Comment("판매제품 제목")
    private String title;

    @Comment("상품가격")
    private int price;

    @Comment("상품 정보 입력")
    private String info;

    @Comment("판매자 위치설정")
    private String sellerLocation;

    @Comment("판매자 파워등록") // String로 준 이유는 시간마다 줄여들게 하기쉬해서
    private String powerRegister;
    
    @Comment("상품판매상태") // Enum으로 변경줄예정
    private int productStatus;

    @Comment("상품거래방식")
    private String productMethod;

    //판매자 회원
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="seller_id" , foreignKey = @ForeignKey(name= "register_IBFK_1"))
    private Member member;

    //옥션은 isAuction 이 true일떄만 적용이 된다;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "register", cascade = CascadeType.ALL)
    private Auction auction;

}
