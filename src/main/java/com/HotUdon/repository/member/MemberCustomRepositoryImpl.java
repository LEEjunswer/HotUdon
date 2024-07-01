package com.HotUdon.repository.member;

import com.HotUdon.model.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import  static com.HotUdon.model.QMember.member;
@Repository
@RequiredArgsConstructor
public class MemberCustomRepositoryImpl implements  MemberCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    @Transactional
    public void loginUpdate(Long memberId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        jpaQueryFactory
                .update(member)
                .set(member.loginDay, LocalDateTime.now().format(formatter))
                .where(member.id.eq(memberId))
                .execute();
    }
}
