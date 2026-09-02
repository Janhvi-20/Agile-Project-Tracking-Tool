package com.JK.The_Work_Bench.service;

import com.JK.The_Work_Bench.modal.PlanType;
import com.JK.The_Work_Bench.modal.Subscription;
import com.JK.The_Work_Bench.modal.User;

public interface SubscriptionServices {

	Subscription createSubscription(User user);

	Subscription getUserSubscription(Long userId) throws Exception;

	Subscription upgradeSubscription(Long userId, PlanType planType);

	boolean validity(Subscription subscription);
}
