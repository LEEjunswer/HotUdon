package com.HotUdon.model;

import lombok.*;
import org.hibernate.annotations.Comment;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeleteMember {

    @Id
    @GeneratedValue
    @Column(name="delete_id", nullable = false)
    private Long id;

    @Comment("유저 회원탈퇴날짜")
    @Column(updatable = false)
    private String deleteDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id",foreignKey = @ForeignKey(name = "delete_IBFK_1"))
    private Member member;
}
