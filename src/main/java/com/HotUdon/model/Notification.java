package com.HotUdon.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    @Id
    @GeneratedValue
    @Column(name = "Notification_id",nullable = false)
    private Long id;

    @Comment("알람내용")
    private String content;
    
    @Comment("찜알림이냐 일반이냐")
    private boolean isDibs;

    @Comment("알람 등록일")
    private String regDate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id",foreignKey = @ForeignKey(name = "Notification_IBFK_1"))
    private  Member member;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name="seller_id", foreignKey =  @ForeignKey(name = "Notification_IBFK_2"))
    private Register register;

    @PrePersist
    protected void onCreate() {
        LocalDateTime dateTime = LocalDateTime.now();
        this.regDate = dateTime.format(DateTimeFormatter.ISO_DATE_TIME);
    }
}
