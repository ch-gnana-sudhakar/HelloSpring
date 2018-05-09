package com.mycompany.myapp;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.mycompany.myapp.dao.NotificationDAO;
import com.mycompany.myapp.domain.NotificationRequest;
import com.mycompany.myapp.domain.NotificationResponse;
import com.mycompany.myapp.domain.TxnApprovalStatus;
import com.mycompany.myapp.domain.UserPushSubscription;

import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import nl.martijndwars.webpush.Subscription;

@Controller

@RequestMapping("/api")
public class APIController {
	@Autowired
	private NotificationDAO notifcationDao;

	@RequestMapping(value="/sendPushNotification")
	public @ResponseBody String sendPushNotification(@RequestParam("nfcid") String nfcid, @RequestParam("vendor") String vendor, @RequestParam("amount") double amount) throws GeneralSecurityException, IOException, JoseException, ExecutionException, InterruptedException {
		System.out.println("Inside APIController.sendPushNotification");

		String reqId = nfcid + System.currentTimeMillis();
		
		UserPushSubscription ups = notifcationDao.getPushSubscriptionById(nfcid);
		
		TxnApprovalStatus approvalStatus = new TxnApprovalStatus();
		approvalStatus.setStatus("I");
		approvalStatus.setTxnReqId(reqId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		approvalStatus.setTxnInitTime(sdf.format(new Date()));
		approvalStatus.setUserId(ups.getUserId());
		approvalStatus.setVendor(vendor);
		approvalStatus.setAmount(""+amount);
		
		notifcationDao.initiateTxnApprovalStatus(approvalStatus);
		
		

		sendWebPushNotification(ups, vendor, amount);
		

		/*NotificationResponse nr = new NotificationResponse();
		nr.setRequestId(reqId);*/
		return reqId;
	}

	@RequestMapping(value="/getTxnApprovalStatus")
	public @ResponseBody String getTxnApprovalStatus(@RequestParam("txnReqId") String txnReqId) {
		TxnApprovalStatus tas = notifcationDao.getTxnApprovalStatus(txnReqId);
		return tas.getStatus();
	}

	public void sendWebPushNotification(UserPushSubscription ups,String vendor,double amount) throws GeneralSecurityException, IOException, JoseException, ExecutionException, InterruptedException {

		Security.addProvider(new BouncyCastleProvider());

		PushService pushService = new PushService()
				.setPublicKey("BMtx9eBN_TyKYIN4ia6TW2ySCVffeH64VkflG8lQTkDss502fm6nSL9blTP9ZBIOQScOWKL6rwO3DzBLq8kD-ys")
				.setPrivateKey("mfEXhUBv5UgvpaJndbsxrlK3bjWWj2dLpPdzGUnqCms")
				.setSubject("mailto:admin@domain.com");


		String subscriptionStr = ups.getSubscription();
		Gson gson = new Gson();
		Subscription subscription = gson.fromJson(subscriptionStr, Subscription.class);

		Notification notification = new Notification(subscription, "Approve to pay " + amount + " @ " + vendor);

		pushService.send(notification);

	}
}
