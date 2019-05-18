package com.cc.springrest.dev.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cc.springrest.dev.dto.OrderDTO;
import com.cc.springrest.dev.model.Basket;
import com.cc.springrest.dev.model.Customer;
import com.cc.springrest.dev.service.BasketService;
import com.cc.springrest.dev.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderService orderService;
	
	@Autowired
	BasketService basketService;
	
	@PostMapping(value = "/create/{customerId}/{basketId}")
	public ResponseEntity<?> create(@PathVariable long customerId, @PathVariable long basketId) {
		
		//check if basket exists
		Basket savedBasket = basketService.findById(basketId);
	    if (null != savedBasket) {
	    	if(CollectionUtils.isEmpty(savedBasket.getOrderItems())) {
	    		return new ResponseEntity<>("Order cannot be created for empty basket", HttpStatus.NOT_FOUND);
	    	}
	    	//check if order is already places for this basket
	    	if(Objects.nonNull(savedBasket.getOrderItems().get(0).getOrder())) {
	    		return new ResponseEntity<>("Order already created for this basket", HttpStatus.BAD_REQUEST);
	    	}
	    	//validate customer id
	    	Customer customer = savedBasket.getCustomer();
	    	if(null != customer && null != customer.getId() && customerId == customer.getId().longValue()) {
	    		Long orderId = orderService.createOrder(customer, savedBasket);
	    		return new ResponseEntity<>(String.format("Successfully created new order with ID : %d", orderId), HttpStatus.CREATED);
	    	} else {
	    		return new ResponseEntity<>("Incorrect customer id sent", HttpStatus.BAD_REQUEST);
	    	}
	    }

	    return new ResponseEntity<>("Invalid basket id - basket not found", HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value = "/getOrdersForCustomer/{customerId}")
	public ResponseEntity<?> getOrdersForCustomer(@PathVariable long customerId) {
		
		List<OrderDTO> orders = orderService.getOrdersForCustomer(customerId);
		if(CollectionUtils.isEmpty(orders)) {
			return new ResponseEntity<>("No orders found for this customer", HttpStatus.NOT_FOUND);
		} 
		
		return new ResponseEntity<>(orders, HttpStatus.FOUND);
	}
	
	@GetMapping(value = "/getOrder/{orderId}")
	public ResponseEntity<?> getOrderById(@PathVariable long orderId) {
		
		OrderDTO order = orderService.getOrderById(orderId);
		if(Objects.isNull(order)) {
			return new ResponseEntity<>("Order Not Found", HttpStatus.NOT_FOUND);
		} 
		
		return new ResponseEntity<>(order, HttpStatus.FOUND);
	}
}
