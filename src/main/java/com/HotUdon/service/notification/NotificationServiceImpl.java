package com.HotUdon.service.notification;

import com.HotUdon.dto.NotificationDTO;
import com.HotUdon.model.Notification;
import com.HotUdon.repository.notification.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService{

    private final NotificationRepository notificationRepository;
    @Override
    public NotificationDTO dibsProduct(NotificationDTO notificationDTO, Long memberId, Long registerId) {
      NotificationDTO notification = new NotificationDTO();
 /*     notification.set*/
        return null;
    }
}
