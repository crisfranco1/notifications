package com.tinsa.notifications.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tinsa.notifications.model.SendNotificationRequest;
import com.tinsa.notifications.model.entities.NotificationLog;
import com.tinsa.notifications.service.NotificationService;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

	@Autowired
	private NotificationService notificationService;

	@PostMapping("/send-sms")
	public ResponseEntity<String> sendSmsNotification(@RequestBody SendNotificationRequest sendNotificationRequest)
			throws IOException {
		String answer = this.notificationService.sendSmsNotification(sendNotificationRequest);
		return new ResponseEntity<>(answer, HttpStatus.CREATED);
	}

	@PostMapping("/send-fax")
	public ResponseEntity<String> sendFaxNotification(@RequestBody SendNotificationRequest sendNotificationRequest)
			throws IOException {
		String answer = this.notificationService.sendFaxNotification(sendNotificationRequest);
		return new ResponseEntity<>(answer, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<NotificationLog>> getAll() {
		List<NotificationLog> notificationlogs = this.notificationService.getAllNotificationLogs();
		return new ResponseEntity<>(notificationlogs, HttpStatus.OK);
	}

}
