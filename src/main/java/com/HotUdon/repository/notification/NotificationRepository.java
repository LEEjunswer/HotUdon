package com.HotUdon.repository.notification;

import com.HotUdon.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, Long>,NotificationCustomRepository {
    Optional<Notification> findByRegisterId(Long registerId);

}
