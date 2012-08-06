package com.pls.domain;

public enum CarrierStatus {
	ACTIVE("Active"),
	INACTIVE("Inactive");
	
	private String humanReadble;
	public String getHumanReadble() {
		return humanReadble;
	}

	private CarrierStatus(String humanReadble){
		this.humanReadble = humanReadble;
	}
}
