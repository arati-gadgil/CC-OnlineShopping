package com.cc.springrest.dev.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cc.springrest.dev.dao.CustomerDAO;
import com.cc.springrest.dev.model.Customer;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerDAO customerDAO;
	
	public List<Customer> getAllCustomers() {
		return customerDAO.getAllCustomers();
	}
}
