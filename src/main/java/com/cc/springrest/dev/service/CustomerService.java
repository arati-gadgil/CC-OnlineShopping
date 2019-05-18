package com.cc.springrest.dev.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cc.springrest.dev.dao.CustomerDAO;
import com.cc.springrest.dev.dto.CustomerDTO;
import com.cc.springrest.dev.model.Customer;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerDAO customerDAO;
	
	public List<CustomerDTO> getAllCustomers() {
		List<Customer> customers = customerDAO.getAllCustomers();
		if(CollectionUtils.isEmpty(customers)) {
			return null;
		}
		
		List<CustomerDTO> customerList = new ArrayList<CustomerDTO>();
		for (Customer customer : customers) {
			CustomerDTO customerDTO = new CustomerDTO(customer);
			customerList.add(customerDTO);
		}
		
		return customerList; 
	}
}
