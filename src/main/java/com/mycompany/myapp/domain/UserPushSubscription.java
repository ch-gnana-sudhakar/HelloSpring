package com.mycompany.myapp.domain;

public class UserPushSubscription {
	private String userId;
	private String subscription;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSubscription() {
		return subscription;
	}
	public void setSubscription(String subscription) {
		this.subscription = subscription;
	}
}
