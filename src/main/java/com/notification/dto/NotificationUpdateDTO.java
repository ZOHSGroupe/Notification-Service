package com.notification.dto;
import com.notification.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class NotificationUpdateDTO {
    @Enumerated(value = EnumType.STRING)
    Status status;
}
