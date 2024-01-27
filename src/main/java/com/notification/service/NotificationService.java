package com.notification.service;

import com.notification.dto.NotificationCreateDTO;
import com.notification.dto.NotificationDTO;
import com.notification.dto.NotificationUpdateDTO;
import com.notification.entity.Notification;
import com.notification.exception.ClientNotExistsException;
import com.notification.exception.NotificationNotFoundException;
import com.notification.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final ModelMapper modelMapper;



    public Optional<NotificationCreateDTO> saveNotification(NotificationCreateDTO notificationCreateDTO) {
        Notification notification = modelMapper.map(notificationCreateDTO, Notification.class);
        notification.setDateNotification(new Date());
        try {
            notification = notificationRepository.save(notification);
            NotificationCreateDTO createdAssuranceDTO = modelMapper.map(notification, NotificationCreateDTO.class);
            return Optional.of(createdAssuranceDTO);
        } catch (Exception e) {
            throw new ClientNotExistsException("Client does not exist with id : "+ notificationCreateDTO.getClientId()+" for notification creation.");
        }
    }
    public NotificationDTO updateNotification(String id, NotificationUpdateDTO updateDriverLicenseDTO) {
        Optional<Notification> existingDriverLicenseOptional = notificationRepository.findById(id);

        if (existingDriverLicenseOptional.isPresent()) {
            Notification existingDriverLicense = existingDriverLicenseOptional.get();
            if (updateDriverLicenseDTO.getStatus() != null) {
                existingDriverLicense.setStatus(updateDriverLicenseDTO.getStatus());
            }
            existingDriverLicense = notificationRepository.save(existingDriverLicense);
            return modelMapper.map(existingDriverLicense, NotificationDTO.class);
        } else {
            throw new NotificationNotFoundException("Notification not found with id: " + id);
        }
    }

    public List<NotificationDTO> getAllANotifications() {
        List<Notification> notifications = notificationRepository.findAll();
        return notifications.stream()
                .map(notification -> modelMapper.map(notification, NotificationDTO.class))
                .collect(Collectors.toList());
    }

    public List<NotificationDTO> getNotificationsByIdClient(String clientId) {
        List<Notification> notifications = notificationRepository.findByClientId(clientId);
        return notifications.stream()
                .map(notification -> modelMapper.map(notification, NotificationDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<NotificationDTO> getNotificationById(String id) {
        return notificationRepository.findById(id)
                .map(notification -> modelMapper.map(notification, NotificationDTO.class));
    }


    public boolean deleteANotificationById(String id) {
        Optional<Notification> existingAssurance = notificationRepository.findById(id);

        if (existingAssurance.isPresent()) {
            try {
                notificationRepository.deleteById(id);
                return true; // Deletion successful
            } catch (Exception e) {
                e.printStackTrace(); // Log the exception
                return false; // Deletion failed
            }
        } else {
            return false; // Assurance not found
        }
    }


    public boolean deleteNotificationsByClientId(String clientId) {
        List<Notification> notificationToDelete = notificationRepository.findByClientId(clientId);
        if(notificationToDelete.isEmpty()) return false;
        notificationToDelete.forEach(notification -> notificationRepository.deleteById(notification.getId()));
        return true;
    }

    // Add other methods as needed, e.g., findByType, findByStatus, etc.
}
