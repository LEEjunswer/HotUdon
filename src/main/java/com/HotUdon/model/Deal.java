package com.HotUdon.model;

import lombok.*;
import org.hibernate.annotations.Comment;
import jakarta.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Deal {
    @Id
    @Column(name="deal_id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("구매종료일자")
    private String endDate;
    
    @Comment("구매자 리뷰")
    private String buyerReview;
    @Comment("구매자 평점")
    private Double buyerGrade;
    @Comment("구매자 삭제날짜") // null일떄만 보이고 아닐떄는 안보이게 해준다
    private String buyerDelete;

    @Comment("판매자 리뷰")
    private String sellerReview;
    @Comment("판매자 평점")
    private Double sellerGrade;
    @Comment("판매자 삭제날짜") // null일떄만 보여주고 날짜 들어갈시 되돌릴수없음
    private String sellerDelete;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id",foreignKey = @ForeignKey(name = "DEAL_IBFK_1"))
    private  Register register;

    //등록자와 member_id 겹치게  못받게 할것
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "buyer_id", foreignKey = @ForeignKey(name= "deal_IBFK_2"))
    private Member member;
}
