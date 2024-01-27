
package com.notification.entity;

import com.notification.enums.Status;
import jakarta.persistence.*;
        import lombok.*;
        import org.hibernate.annotations.GenericGenerator;

import java.util.Date;


@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    private String id;

    @Temporal(TemporalType.DATE)
    private Date dateNotification;

    @Enumerated(value = EnumType.STRING)
    Status status;

    String clientId;

    String message;



}
