package com.HotUdon.service.notification;

import com.HotUdon.dto.NotificationDTO;

public interface NotificationService {
    NotificationDTO  dibsProduct(NotificationDTO notificationDTO,Long memberId,Long registerId );
}
