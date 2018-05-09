package com.mycompany.myapp;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Security;
import java.util.concurrent.ExecutionException;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.mycompany.myapp.dao.NotificationDAO;
import com.mycompany.myapp.domain.NotificationResponse;
import com.mycompany.myapp.domain.UserPushSubscription;

import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import nl.martijndwars.webpush.Subscription;

@Controller

@RequestMapping("/api")
public class APIController {
	@Autowired
	private NotificationDAO notifcationDao;
	@RequestMapping(value="/sendPushNotification/{nfcid}")
	public @ResponseBody NotificationResponse sendPushNotification(@PathVariable("nfcid") String nfcid) throws GeneralSecurityException, IOException, JoseException, ExecutionException, InterruptedException {
		Security.addProvider(new BouncyCastleProvider());
		
		PushService pushService = new PushService()
	            .setPublicKey("BMtx9eBN_TyKYIN4ia6TW2ySCVffeH64VkflG8lQTkDss502fm6nSL9blTP9ZBIOQScOWKL6rwO3DzBLq8kD-ys")
	            .setPrivateKey("mfEXhUBv5UgvpaJndbsxrlK3bjWWj2dLpPdzGUnqCms")
	            .setSubject("mailto:admin@domain.com");
		
		UserPushSubscription ups = notifcationDao.getPushSubscriptionById(nfcid);
		String subscriptionStr = ups.getSubscription();
		Gson gson = new Gson();
		Subscription subscription = gson.fromJson(subscriptionStr, Subscription.class);
		
		Notification notification = new Notification(subscription, "Hello Sudhakar");
		
		pushService.send(notification);
		
		NotificationResponse nr = new NotificationResponse();
		nr.setRequestId(nfcid + System.currentTimeMillis());
		return nr;
	}

}
