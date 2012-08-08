package com.pls.domain;

import java.util.Date;

public class Customer {
	private Long id;
	
	private String name;
	
	private String taxId;
	
	private String contactName;
	
	private Date validUntil;

	private CustomerStatus status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public CustomerStatus getStatus() {
		return status;
	}

	public void setStatus(CustomerStatus status) {
		this.status = status;
	}

	public Date getValidUntil() {
		return validUntil;
	}

	public void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	@Override
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}
		
		if (!(object instanceof Customer)) {
			return false;
		}
		
		Customer customer = (Customer) object;
		return this.id.equals(customer.id);
	}
	
	@Override
	public int hashCode() {
		return this.id.hashCode();
	}
}
