package com.mycompany.myapp.domain;

public class TxnApprovalStatus {
	
	private String status;
	private String txnInitTime;
	private String txnApprTime;
	private String txnReqId;
	private String userId;
	private String vendor;
	private String amount;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTxnInitTime() {
		return txnInitTime;
	}
	public void setTxnInitTime(String txnInitTime) {
		this.txnInitTime = txnInitTime;
	}
	public String getTxnApprTime() {
		return txnApprTime;
	}
	public void setTxnApprTime(String txnApprTime) {
		this.txnApprTime = txnApprTime;
	}
	public String getTxnReqId() {
		return txnReqId;
	}
	public void setTxnReqId(String txnReqId) {
		this.txnReqId = txnReqId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
}
