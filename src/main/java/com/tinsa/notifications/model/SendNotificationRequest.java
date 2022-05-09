package com.tinsa.notifications.model;

public class SendNotificationRequest {

	private Long userId;
	private String phone;
	private String message;

	public SendNotificationRequest(Long userId, String phone, String message) {
		super();
		this.userId = userId;
		this.phone = phone;
		this.message = message;

	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public long getUserId() {
		return userId;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
