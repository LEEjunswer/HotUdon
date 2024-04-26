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
