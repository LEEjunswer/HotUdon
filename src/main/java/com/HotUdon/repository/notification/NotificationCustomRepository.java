package com.HotUdon.repository.notification;

import com.HotUdon.model.Notification;

import java.util.List;

public interface NotificationCustomRepository {
    List<Notification> findByMemberIdAndDibsTrue(Long  memberId);
    List<Notification>  findByRegisterIdInAndMemberIdAndDibsTrue(List<Long> registerIds, Long memberId);
}
