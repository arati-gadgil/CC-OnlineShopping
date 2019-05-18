package com.cc.springrest.dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cc.springrest.dev.dto.CustomerDTO;
import com.cc.springrest.dev.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/getAllCustomers")
	public ResponseEntity<?> getAllCustomers() {
		List<CustomerDTO> customers = customerService.getAllCustomers();
		
		if(CollectionUtils.isEmpty(customers)) {
			return new ResponseEntity<>("No customers found", HttpStatus.NOT_FOUND);
		} 
		
		return new ResponseEntity<>(customers, HttpStatus.FOUND);
	}
}
