package com.notification.dto;


import com.notification.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class NotificationDTO {

    String id;
    @Temporal(TemporalType.DATE)
    Date dateNotification;

    @Enumerated(value = EnumType.STRING)
    Status status;

    String clientId;

    String message;


}
