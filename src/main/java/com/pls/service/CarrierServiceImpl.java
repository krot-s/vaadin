package com.pls.service;

import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import com.pls.domain.Carrier;
import com.pls.domain.CarrierStatus;

public class CarrierServiceImpl implements CarrierService {
	private final List<Carrier> carriers;
	
	public CarrierServiceImpl() {
		carriers = new Vector<Carrier>(50);
		for(long i = 0; i < 50; i++){
			carriers.add(createCarrier(i));
		}
	}

	@Override
	public Carrier getById(Long id) {
		for(Carrier carrier : carriers){
			if(carrier.getId().equals(id)){
				return carrier;
			}
		}
		
		return null;
	}

	@Override
	public List<Carrier> getAllCarriers() {
		return carriers;
	}

	private static Carrier createCarrier(Long id){
		Carrier carrier = new Carrier();
		carrier.setId(id);
		carrier.setContactName("Kolya Ananymous");
		carrier.setName("Roga i Kopita");
		carrier.setStatus(CarrierStatus.ACTIVE);
		carrier.setTaxId("0000-1111-2222");
		carrier.setValidUntil(Calendar.getInstance().getTime());
		carrier.setMcNumber(110011l);
		carrier.setScac("KANN");
		return carrier;
	}

	public void addCarrier(Carrier carrier){
		carriers.add(carrier);
	}
}
