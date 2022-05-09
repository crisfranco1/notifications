package com.tinsa.notifications.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "notificationSetting")
public class NotificationSetting {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Long userId;
	private boolean isSmsActive;
	private boolean isFaxActive;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getUserId() {
		return userId;
	}

	public void setSmsActive(boolean isSmsActive) {
		this.isSmsActive = isSmsActive;
	}

	public boolean isSmsActive() {
		return isSmsActive;
	}

	public void setFaxActive(boolean isFaxActive) {
		this.isFaxActive = isFaxActive;
	}

	public boolean isFaxActive() {
		return isFaxActive;
	}

}
