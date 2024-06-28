package com.HotUdon.repository.notification;

import com.HotUdon.model.Notification;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import static com.HotUdon.model.QNotification.notification;
@Repository
@RequiredArgsConstructor
public class NotificationCustomRepositoryImpl implements NotificationCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    /*좀 다르개  where 로 다 넣어버리네 and가 안된다.. */
    @Override
    public List<Notification> findByMemberIdAndDibsTrue(Long memberId) {
        return jpaQueryFactory
                .selectFrom(notification)
                .where(notification.member.id.eq(memberId),notification.isDibs.isTrue())
                .fetch();
    }

    @Override
    public List<Notification> findAllByRegisterIdInAndMemberIdAndDibsTrue(List<Long> registerIds, Long memberId) {
        /*List로 가져올 경우 in을 쓴다.*/

        List<Notification> results = jpaQueryFactory
                .selectFrom(notification)
                .where(notification.register.id.in(registerIds), notification.member.id.eq(memberId), notification.isDibs.isTrue())
                .fetch();

        System.out.println("Query Results: " + results);

        return results;
    }

    @Override
    public Notification findByRegisterIdAndMemberIdAndDibsTrue(Long register, Long memberId) {

        return  jpaQueryFactory.selectFrom(notification)
                .where(notification.isDibs.isTrue(),notification.register.id.eq(register),notification.member.id.eq(memberId))
                .fetchOne();
    }
}
