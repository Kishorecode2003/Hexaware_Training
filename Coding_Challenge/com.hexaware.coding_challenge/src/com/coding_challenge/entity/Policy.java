package com.coding_challenge.entity;

public class Policy {
	private int policyId;
	private String policyName;
	private String policyType;
	private double Amount;

	public Policy(int policyId, String policyName, String policyType, double amount) {
		this.policyId = policyId;
		this.policyName = policyName;
		this.policyType = policyType;
		this.Amount = amount;
	}

	public Policy(String policyName, String policyType, double amount) {
		this.policyName = policyName;
		this.policyType = policyType;
		this.Amount = amount;
	}

	@Override
	public String toString() {
		return "Policy [policyId=" + policyId + ", policyName=" + policyName + ", policyType=" + policyType
				+ ", Amount=" + Amount + "]";
	}

	public int getPolicyId() {
		return policyId;
	}

	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	public double getAmount() {
		return Amount;
	}

	public void setAmount(double amount) {
		Amount = amount;
	}
}
