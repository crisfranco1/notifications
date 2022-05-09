package com.tinsa.notifications.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tinsa.notifications.model.entities.NotificationSetting;
import com.tinsa.notifications.service.NotificationService;

@RestController
@RequestMapping("/notification-settings")
public class NotificationSettingController {

	@Autowired
	private NotificationService notificationService;

	@PostMapping
	public ResponseEntity<NotificationSetting> create(@RequestBody NotificationSetting notificationSetting) {
		NotificationSetting createdNotificationSetting = this.notificationService
				.createNotificationSetting(notificationSetting);
		return new ResponseEntity<>(createdNotificationSetting, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<NotificationSetting>> getAll() {
		List<NotificationSetting> notificationSettings = this.notificationService.getAllNotificationSettings();
		return new ResponseEntity<>(notificationSettings, HttpStatus.OK);
	}

}
