package com.tinsa.notifications.model.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tinsa.notifications.model.NotificationState;
import com.tinsa.notifications.model.NotificationType;

@Entity
@Table(name = "notificationLog")
public class NotificationLog {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private NotificationType type;

	private Date datetime;
	private Long userId;
	private String phone;
	private String message;
	private NotificationState state;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setType(NotificationType type) {
		this.type = type;
	}

	public NotificationType getType() {
		return type;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setUserId(long userId) {
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

	public void setState(NotificationState state) {
		this.state = state;
	}

	public NotificationState getState() {
		return state;
	}

}
