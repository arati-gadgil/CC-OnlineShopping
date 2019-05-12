package com.cc.springrest.dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cc.springrest.dev.model.Customer;
import com.cc.springrest.dev.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/getAllCustomers")
	public List<Customer> getAllCustomers() {
		return customerService.getAllCustomers();
	}
	
//	@GetMapping("/getOrdersForCustomer")
//	public List<Basket> getOrdersForCustomer() {
//		return null;
//	}
}
