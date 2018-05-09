package com.mycompany.myapp.domain;

public class NotificationRequest {
	
	private String nfcid;
	private String vendorName;
	private double amount;
	public String getNfcid() {
		return nfcid;
	}
	public void setNfcid(String nfcid) {
		this.nfcid = nfcid;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
}
