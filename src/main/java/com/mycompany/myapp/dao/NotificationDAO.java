package com.mycompany.myapp.dao;

import com.mycompany.myapp.domain.TxnApprovalStatus;
import com.mycompany.myapp.domain.UserPushSubscription;

public interface NotificationDAO {
	public UserPushSubscription getPushSubscriptionById(String user_id);
	public TxnApprovalStatus getTxnApprovalStatus(String txnReqId);
	public TxnApprovalStatus initiateTxnApprovalStatus(TxnApprovalStatus approvalStatus);
	public TxnApprovalStatus getTransactioInfoByUserId(String userID);
	public TxnApprovalStatus updateTxnInfo(TxnApprovalStatus approvalStatus);
}
