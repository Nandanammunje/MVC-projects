package com.rest.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rest.entity.Customer;
@Repository
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public void saveCustomer(Customer cust) {
		// TODO Auto-generated method stub
  Session current=sessionFactory.getCurrentSession();
     cust.setBody(cust.getBody().substring(5));
     System.out.print(cust.getBody());
      current.saveOrUpdate(cust);
	}

}
