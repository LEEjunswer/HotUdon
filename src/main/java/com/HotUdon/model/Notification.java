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
public class Notification {

    @Id
    @GeneratedValue
    @Column(name = "Notification_id",nullable = false)
    private Long id;

    @Comment("알람내용")
    private String content;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id",foreignKey = @ForeignKey(name = "Notification_IBFK_1"))
    private  Member member;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name="seller_id", foreignKey =  @ForeignKey(name = "Notification_IBFK_2"))
    private Register register;
}
