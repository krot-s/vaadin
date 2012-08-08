package com.pls.service;

import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import com.pls.domain.Customer;
import com.pls.domain.CustomerStatus;

public class CustomerServiceImpl implements CustomerService {
	private final List<Customer> customers;
	
	public CustomerServiceImpl() {
		customers = new Vector<Customer>(50);
		for(long i = 0; i < 50; i++){
			customers.add(createCustomer(i));
		}
	}

	@Override
	public Customer getById(Long id) {
		for(Customer customer : customers){
			if(customer.getId().equals(id)){
				return customer;
			}
		}
		return null;
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customers;
	}

	private static Customer createCustomer(Long id){
		Customer customer = new Customer();
		customer.setId(id);
		customer.setContactName("Vasya Pupkin");
		customer.setName("Pupkin & Sons");
		customer.setStatus(CustomerStatus.ACTIVE);
		customer.setTaxId("0000-1111-2222");
		customer.setValidUntil(Calendar.getInstance().getTime());
		return customer;
	}
	
	public void addCustomer(Customer customer){
		customers.add(customer);
	}
}
