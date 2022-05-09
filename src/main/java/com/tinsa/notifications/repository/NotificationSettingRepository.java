package com.tinsa.notifications.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tinsa.notifications.model.entities.NotificationSetting;

@Repository
public interface NotificationSettingRepository extends JpaRepository<NotificationSetting, Long> {
	Optional<NotificationSetting> findByUserId(Long userId);
}
