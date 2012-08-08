package com.pls.service;

import java.util.List;

import com.pls.domain.Carrier;

public interface CarrierService {
	public Carrier getById(Long id);
	
	public List<Carrier> getAllCarriers();
	
	public void addCarrier(Carrier carrier);
}
