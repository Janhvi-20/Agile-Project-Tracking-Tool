package com.JK.The_Work_Bench.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JK.The_Work_Bench.modal.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
	Subscription findByUserID(Long userId);

}
