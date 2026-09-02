package com.JK.The_Work_Bench.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JK.The_Work_Bench.modal.PlanType;
import com.JK.The_Work_Bench.modal.Subscription;
import com.JK.The_Work_Bench.modal.User;
import com.JK.The_Work_Bench.repository.SubscriptionRepository;

@Service
public class SubscriptionServicesImpl implements SubscriptionServices {


	@Autowired
	private SubscriptionRepository subscriptionRepository;

	@Override
	public Subscription createSubscription(User user) {
		Subscription subscription = new Subscription();

		subscription.setUser(user);
		subscription.setSubscriptionStartDteDate(LocalDate.now());
		subscription.setGetSubscriptionEndDate(LocalDate.now().plusMonths(12));
		subscription.setValid(true);
		subscription.setPlanType(PlanType.FREE);

		return subscriptionRepository.save(subscription);
	}

	@Override
	public Subscription getUserSubscription(Long userId) throws Exception {
		Subscription subscription = subscriptionRepository.findByUserID(userId);
		if (!subscription.isValid()) {
			subscription.setPlanType(PlanType.FREE);
			subscription.setGetSubscriptionEndDate(LocalDate.now().plusMonths(12));
			subscription.setSubscriptionStartDteDate(LocalDate.now());

		}
		return subscriptionRepository.save(subscription);
	}

	@Override
	public Subscription upgradeSubscription(Long userId, PlanType planType) {
		Subscription subscription = subscriptionRepository.findByUserID(userId);
		subscription.setPlanType(planType);
		subscription.setSubscriptionStartDteDate(LocalDate.now());
		if (planType.equals(PlanType.ANNUALLY)) {
			subscription.setGetSubscriptionEndDate(LocalDate.now().plusMonths(12));
		} else {
			subscription.setGetSubscriptionEndDate(LocalDate.now().plusMonths(1));
		}
		return subscriptionRepository.save(subscription);
	}

	@Override
	public boolean validity(Subscription subscription) {
		if (subscription.getPlanType().equals(PlanType.FREE)) {
			return true;
		}
		LocalDate endDate = subscription.getGetSubscriptionEndDate();
		LocalDate currentDate = LocalDate.now();
		return endDate.isAfter(currentDate) | endDate.isEqual(currentDate);
	}

}
