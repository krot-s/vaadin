package com.pls.domain;
import java.math.BigDecimal;

import com.pls.domain.Carrier;
import com.pls.domain.Customer;


public class Load {
	private Long id;
	
	private String shipmentNumber;
	
	private Integer piecies;
	
	private Integer weight;
	
	private Customer customer;
	
	private Carrier carrier;
	
	private BigDecimal customerRate;
	
	private BigDecimal carrierRate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShipmentNumber() {
		return shipmentNumber;
	}

	public void setShipmentNumber(String shipmentNumber) {
		this.shipmentNumber = shipmentNumber;
	}

	public Integer getPiecies() {
		return piecies;
	}

	public void setPiecies(Integer piecies) {
		this.piecies = piecies;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Carrier getCarrier() {
		return carrier;
	}

	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}

	public BigDecimal getCustomerRate() {
		return customerRate;
	}

	public void setCustomerRate(BigDecimal customerRate) {
		this.customerRate = customerRate;
	}

	public BigDecimal getCarrierRate() {
		return carrierRate;
	}

	public void setCarrierRate(BigDecimal carrierRate) {
		this.carrierRate = carrierRate;
	}
}
