package com.cc.springrest.dev.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cc.springrest.dev.model.Customer;

@Repository
public class CustomerDAO extends BaseDAO {

	@SuppressWarnings("unchecked")
	public List<Customer> getAllCustomers() {	
		return getSession().createCriteria(Customer.class).list();

	}

}
