package com.pls.domain;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Carrier{
	@NotNull
	private Long id = 1l;
	
	@NotNull
	private String name;
	
	@NotNull
	private CarrierStatus status;
	
	@NotNull
	private String scac;
	
	@NotNull
	private Long mcNumber;
	
	@NotNull
	private Date validUntil;
	
	@NotNull
    @Pattern(regexp="[0-9]{4}-[0-9]{4}-[0-9]{4}")
	private String taxId;
	
	@NotNull
	private String contactName;

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

	public CarrierStatus getStatus() {
		return status;
	}

	public void setStatus(CarrierStatus status) {
		this.status = status;
	}

	public String getScac() {
		return scac;
	}

	public void setScac(String scac) {
		this.scac = scac;
	}

	public Long getMcNumber() {
		return mcNumber;
	}

	public void setMcNumber(Long mcNumber) {
		this.mcNumber = mcNumber;
	}

	public Date getValidUntil() {
		return validUntil;
	}

	public void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
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
	
	@Override
	public String toString() {
		return this.name;
	}
	
	@Override
	public boolean equals(Object object) {
		if (object == null ) {
			return false;
		}
		
		if (!(object instanceof Carrier)) {
			return false;
		}
		
		Carrier carrier = (Carrier)object;

		return this.id.equals(carrier.id);
	}
	
	@Override
	public int hashCode() {
		return this.id.hashCode();
	}
}
