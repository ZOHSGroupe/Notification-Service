package com.notification.repository;

    import com.notification.entity.Notification;
    import com.notification.enums.Status;
    import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,String> {
    List<Notification> findByClientId(String clientId);
}
