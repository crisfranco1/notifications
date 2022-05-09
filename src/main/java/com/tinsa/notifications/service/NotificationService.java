package com.tinsa.notifications.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.squareup.okhttp.Response;
import com.tinsa.notifications.model.SendNotificationRequest;
import com.tinsa.notifications.model.entities.NotificationLog;
import com.tinsa.notifications.model.entities.NotificationSetting;
import com.tinsa.notifications.repository.NotificationLogRepository;
import com.tinsa.notifications.repository.NotificationSettingRepository;

@Service
public class NotificationService {

	@Autowired
	private NotificationSettingRepository notificationSettingRepository;

	@Autowired
	private NotificationLogRepository notificationLogRepository;

	@Autowired
	private AlternateService alternateService;

	// Notification settings

	public NotificationSetting createNotificationSetting(NotificationSetting notificationSetting) {
		return this.notificationSettingRepository.save(notificationSetting);
	}

	public List<NotificationSetting> getAllNotificationSettings() {
		return this.notificationSettingRepository.findAll();
	}

	public Optional<NotificationSetting> getNotificationSettingByUserId(Long userId) {
		return this.notificationSettingRepository.findByUserId(userId);
	}

	// Notifications

	public String sendSmsNotification(SendNotificationRequest sendNotificationRequest) throws IOException {

		Optional<NotificationSetting> optNotificationSetting = this
				.getNotificationSettingByUserId(sendNotificationRequest.getUserId());

		if (optNotificationSetting.isPresent()) {
			NotificationSetting notificationSetting = optNotificationSetting.get();
			if (notificationSetting.isSmsActive()) {

				Response smsRes = this.alternateService.sendSms(sendNotificationRequest.getUserId(),
						sendNotificationRequest.getPhone(), sendNotificationRequest.getMessage());

				if (smsRes.code() == HttpStatus.OK.value()) {
					return "SMS: success.";
				} else {
					return "SMS: fail.";
				}
			} else {
				return "SMS notification setting is off.";
			}
		} else {
			return "User does not exist.";
		}
	}

	public String sendFaxNotification(SendNotificationRequest sendNotificationRequest) throws IOException {

		Optional<NotificationSetting> optNotificationSetting = this
				.getNotificationSettingByUserId(sendNotificationRequest.getUserId());

		if (optNotificationSetting.isPresent()) {
			NotificationSetting notificationSetting = optNotificationSetting.get();
			if (notificationSetting.isSmsActive()) {

				Response faxRes = this.alternateService.sendFax(sendNotificationRequest.getUserId(),
						sendNotificationRequest.getPhone(), sendNotificationRequest.getMessage());

				if (faxRes.code() == HttpStatus.OK.value()) {
					return "FAX: success.";
				} else {
					return "FAX: fail.";
				}
			} else {
				return "FAX notification setting is off.";
			}
		} else {
			return "User does not exist.";
		}
	}

	public List<NotificationLog> getAllNotificationLogs() {
		return this.notificationLogRepository.findAll();
	}

}
