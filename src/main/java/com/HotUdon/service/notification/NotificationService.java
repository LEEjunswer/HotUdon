package com.HotUdon.service.notification;

import com.HotUdon.dto.NotificationDTO;
import com.HotUdon.model.Member;

import java.util.List;

public interface NotificationService {
    String dibsProduct(Member member, Long registerId);
    String undibsProduct(Member member,Long registerId);
    List<NotificationDTO> myDibsProducts(Long memberId);
    List<NotificationDTO> findByRegisterIdInAndMemberId(List<Long> registerIds, Long memberId);

}
