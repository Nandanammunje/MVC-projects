package com.rest.service;
import com.rest.dao.*;
import java.util.List;

import org.apache.jcs.JCS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.rest.entity.Customer;
@Service
public class CustomerServiceimpl implements  CustomerService{
	
     @Autowired
	RestTemplate resttemplate;
     
     @Autowired
	private CustomerDao customer;
	
	@Override
	public List<Customer> getcustomer() {
		// TODO Auto-generated method stub
		JCS cache;
		try
		{
		List<Customer> customer;
		
	
		cache=JCS.getInstance("customercache");
		if(cache.get("post")!=null)
		{
			 customer=(List<Customer>) cache.get("post");
			 System.out.println("fetching response from cache");
		}
		else
		{
			ResponseEntity<List<Customer>> response=resttemplate.exchange("https://jsonplaceholder.typicode.com/posts",HttpMethod.GET, null, 
					new ParameterizedTypeReference<List<Customer>>() {});
			 customer=response.getBody();
			 System.out.println("fetching response from service");
		      cache.put("post",customer);
		}
		return customer;
	}
		catch(Exception e)
		{
				e.printStackTrace();
		}
		return null;

	}
     @Transactional
	@Override
	public void saveCustomer(Customer cust) {
		// TODO Auto-generated method stub
		customer.saveCustomer(cust);
	}
	@Override
	public void cacherefresh() {
		// TODO Auto-generated method stub
		JCS cache;
		try
		{
			cache=JCS.getInstance("customercache");
			cache.clear();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
