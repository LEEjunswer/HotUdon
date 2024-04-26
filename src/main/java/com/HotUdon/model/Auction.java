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
public class Auction {

    @Id
    @GeneratedValue
    @Column(name = "auction_id")
    private Long id;

    @Comment("경매최종가격")
    private int endPrice;

    @Comment("경매입찰자수")
    private int bidCnt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "register_id",foreignKey =@ForeignKey(name = "register_IBFK_1"))
    private  Register register;
}
