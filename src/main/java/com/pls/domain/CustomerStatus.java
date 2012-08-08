package com.pls.domain;

public enum CustomerStatus {
	ACTIVE("Active"),
	INACTIVE("Inactive");
	
	private String humanReadble;
	public String getHumanReadble() {
		return humanReadble;
	}

	private CustomerStatus(String humanReadble){
		this.humanReadble = humanReadble;
	}
}
