package com.tinsa.notifications.service;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.tinsa.notifications.model.NotificationState;
import com.tinsa.notifications.model.NotificationType;
import com.tinsa.notifications.model.entities.NotificationLog;
import com.tinsa.notifications.repository.NotificationLogRepository;

@Service
public class AlternateService {

	@Autowired
	private NotificationLogRepository notificationLogRepository;

	private static final String SMS_ENDPOINT = "http://localhost:9100/tinsa/sms?";
	private static final String FAX_ENDPOINT = "http://localhost:9100/tinsa/fax?";

	public Response sendSms(Long userId, String phone, String message) throws IOException {
		// Consume service
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(SMS_ENDPOINT + "phone=" + phone + "&message=" + message).build();
		Call call = client.newCall(request);
		Response response = call.execute();

		// Create log
		NotificationState notificationState = (response.code() == HttpStatus.OK.value()) ? NotificationState.SUCCESS
				: NotificationState.FAIL;
		NotificationLog notificationLog = new NotificationLog();
		notificationLog.setType(NotificationType.SMS);
		notificationLog.setDatetime(new Date());
		notificationLog.setUserId(userId);
		notificationLog.setPhone(phone);
		notificationLog.setMessage(message);
		notificationLog.setState(notificationState);
		this.createNotificationLog(notificationLog);

		return response;
	}

	public Response sendFax(Long userId, String phone, String message) throws IOException {
		// Consume service
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(FAX_ENDPOINT + "phone=" + phone + "&message=" + message).build();
		Call call = client.newCall(request);
		Response response = call.execute();

		// Create log
		NotificationState notificationState = (response.code() == HttpStatus.OK.value()) ? NotificationState.SUCCESS
				: NotificationState.FAIL;
		NotificationLog notificationLog = new NotificationLog();
		notificationLog.setType(NotificationType.FAX);
		notificationLog.setDatetime(new Date());
		notificationLog.setUserId(userId);
		notificationLog.setPhone(phone);
		notificationLog.setMessage(message);
		notificationLog.setState(notificationState);
		this.createNotificationLog(notificationLog);

		return response;
	}

	public NotificationLog createNotificationLog(NotificationLog notificationLog) {
		return this.notificationLogRepository.save(notificationLog);
	}

}
