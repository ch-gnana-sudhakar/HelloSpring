package com.mycompany.myapp.dao;

import com.mycompany.myapp.domain.UserPushSubscription;

public interface NotificationDAO {
	public UserPushSubscription getPushSubscriptionById(String user_id);
}
