## Description


The Notification Service is an Application Programming Interface (API) designed to handle notifications for the ZOHS company. It enables operations such as creating, retrieving, updating, and deleting notifications associated with clients.
DriverLicense-Service is an Application Programming interface for add,update,delete and get driver license of ZOHS company.

---
## Running the app
```bash
# build app
$ mvn clean install
# run the app
$ java -jar target/notification-service.jar
```
---
## Dockerize the app
```bash
# dockerize the app with mysql database
$ docker compose up
# stop the application
$ docker compose down
```
---

## DTOs (Data Transfer Objects) for Notification

### 1. NotificationCreateDTO

Represents the data required to create a new notification.

- **Fields:**
    - `status: Status` - Status of the driver license.
    - `clientId: String`- ID of the associated client.
    - `message: String` - Notification message.


### 2. NotificationDTO

Represents the data of a notification.

- **Fields:**
  - `id: string` - Unique identifier of the Notification.
  - `dateNotification: Date` - Date when the notification was created.
  - `clientId: String`- ID of the associated client.
  - `message: String` - Notification message.

### 3. NotificationUpdateDTO

Represents the data used to update an existing notification.

- **Fields:**
  - `status: Status` - Updated status of the driver license.

---

## Available Endpoints

### 1. Save Notification

- **Endpoint:** `POST /notification`
- **Description:** Save a new notification.
- **Request Body:**
   - `NotificationCreateDTO`: Data for creating a new driver license.
- **Response:**
  - `201`: Notification created successfully.
  - `400`: Bad Request (invalid input).
  - `404`: Client not found.
  - `500`: Internal Server Error.

### 2. Get All Notifications

- **Endpoint:** `GET /notification`
- **Description:** Retrieve a list of all notifications.
    - `200`: Successful retrieval with a list of notifications.
    - `500`: Internal Server Error.

### 3. Get Notification by ID

- **Endpoint:** `GET /notification/{id}`
- **Description:** Get details of a notification by ID.
- **Response:**
    - `200`: Successful retrieval with notification details.
    - `404`: Notification not found.
    - `500`: Internal Server Error.

### 4. Get Notifications by Client ID

- **Endpoint:** `GET /notification/client/{clientId}`
- **Description:** Retrieve notifications associated with a specific client ID.
- **Response:**
    - `200`: Successful retrieval with a list of notifications.
    - `500`: Internal Server Error.

### 5. Update Notification

- **Endpoint:** `PUT /notification/{id}`
- **Description:** Update an existing notification by ID.
- - **Request Body:**
- `NotificationUpdateDTO`: Data for updating a notification.
- **Response:**
    - `200`: Successful retrieval with a list of valid driver licenses.
    - `400`: Bad Request.
    - `500`: Internal Server Error.

### 6. Delete Notification by ID

- **Endpoint:** `DELETE /notification/{id}`
- **Description:** Delete a notification by ID.
- **Response:**
    - `200`: Notification deleted successfully.
    - `400`: Notification deleted successfully.
    - `500`: Internal Server Error.

### 7. Delete Notifications by Client ID

- **Endpoint:** `DELETE /notification/client/{clientId}`
- **Description:** Delete notifications associated with a specific client ID.
- **Request Body:**
  - `DriverLicenseUpdateDTO`: Data for updating a driver license.
- **Response:**
    - `200`: Notification updated successfully.
    - `404`: Notification not found.
    - `500`: Internal Server Error.


  

---





## Stay in touch
- Author - [Ouail Laamiri](https://www.linkedin.com/in/ouaillaamiri/)

## License

Notification Service is [GPL licensed](LICENSE).**