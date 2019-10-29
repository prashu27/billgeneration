package com.xebia.assessment.BillGeneration.model;

/**
 * @author prashansa.shukla
 *  
 * Created model class for data mapping and sending back to client which has been shown as the JSON response
 *
 */
public class CustomerBillingData {

	private String userType;
	private double userExperience;
	private double billAmt;
	private String billType;
	private double amtAfterDiscount;

	public double getAmtAfterDiscount() {
		return amtAfterDiscount;
	}

	public void setAmtAfterDiscount(double amtAfterDiscount) {
		this.amtAfterDiscount = amtAfterDiscount;
	}

	public CustomerBillingData() {
		super();
	}

	public CustomerBillingData(String userType, double userExperience, double billAmt, String billType) {
		super();
		this.userType = userType;
		this.userExperience = userExperience;
		this.billAmt = billAmt;
		this.billType = billType;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public double getUserExperience() {
		return userExperience;
	}
	public void setUserExperience(double userExperience) {
		this.userExperience = userExperience;
	}
	public double getBillAmt() {
		return billAmt;
	}
	public void setBillAmt(double billAmt) {
		this.billAmt = billAmt;
	}
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}
	@Override
	public String toString() {
		return "CustomerBillingData [userName=" + userType + ", userExperience=" + userExperience + ", billAmt="
				+ billAmt + ", billType=" + billType + "]";
	}

}
