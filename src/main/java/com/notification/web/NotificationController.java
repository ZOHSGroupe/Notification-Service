package com.notification.web;

import com.notification.dto.ApiResponse;
import com.notification.dto.NotificationCreateDTO;
import com.notification.dto.NotificationDTO;
import com.notification.dto.NotificationUpdateDTO;
import com.notification.exception.ClientNotExistsException;
import com.notification.exception.NotValidNotification;
import com.notification.exception.NotificationNotFoundException;
import com.notification.service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<Object> saveNotification(@RequestBody NotificationCreateDTO notificationCreateDTO) {
        try {
            NotificationCreateDTO savedDriverLicence = notificationService.saveNotification(notificationCreateDTO).orElse(null);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedDriverLicence);
        }catch (ClientNotExistsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
        }catch (NotValidNotification e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateNotification(@PathVariable String id,
                                                      @RequestBody NotificationUpdateDTO updateDriverLicenseDTO) {
        try {
            NotificationDTO updatedDriverLicense = notificationService.updateNotification(id, updateDriverLicenseDTO);
            return ResponseEntity.ok(updatedDriverLicense);
        } catch (NotificationNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
        }
    }




    @GetMapping(value = {"","/","/all"})
    public List<NotificationDTO> getAllDriverLicenses() {
        return notificationService.getAllANotifications();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNotificationById(@PathVariable String id) {
        Optional<NotificationDTO> driverLicenseOptional = notificationService.getNotificationById(id);

        if (driverLicenseOptional.isPresent()) {
            return ResponseEntity.ok(driverLicenseOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse("Notification not found with id: " + id));
        }
    }




    @GetMapping("/client/{clientId}")
    public List<NotificationDTO> getNotificationsByIdClient(@PathVariable String clientId) {
        return notificationService.getNotificationsByIdClient(clientId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteANotificationById(@PathVariable String id) {
        if (notificationService.deleteANotificationById(id)) {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Notification deleted successuflly with id : "+id));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Notification not exist with id : "+id));
        }
    }

    @DeleteMapping("/client/{clientId}")
    public ResponseEntity<?> deleteNotificationsByClientId(@PathVariable String clientId) {
        if (notificationService.deleteNotificationsByClientId(clientId)) {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Notification deleted successuflly with id client : "+clientId));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Notification not exist with id client : "+clientId));
        }
    }
    @GetMapping("/test")
    public ResponseEntity<ApiResponse> test(){
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Notification service work successuflly:"));
    }
}
