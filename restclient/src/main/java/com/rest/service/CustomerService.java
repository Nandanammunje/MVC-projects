package com.rest.service;

import java.util.List;

import com.rest.entity.Customer;

public interface CustomerService {

	public List<Customer> getcustomer();
	
	public void saveCustomer(Customer cust);
	public void cacherefresh();
	
}