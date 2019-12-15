package com.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.rest.entity.Customer;
import com.rest.service.CustomerService;

@Controller
public class Datacontroller {
    @Autowired
    CustomerService service;
    
    @GetMapping("/")
    public String savecustomer()
    {
    	for(Customer cust:service.getcustomer())
    	service.saveCustomer(cust);
		return "index";
    	
    }
	@GetMapping("/refresh")
	public String cacherefresh()
	{
		service.cacherefresh();
		return "index";
	}
}
