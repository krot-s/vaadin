package com.pls.service;

import java.util.List;

import com.pls.domain.Customer;

public interface CustomerService {
	public Customer getById(Long id);
	
	public List<Customer> getAllCustomers();
	
	public void addCustomer(Customer customer);
	
}
