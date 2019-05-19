package com.cc.springrest.dev.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cc.springrest.dev.model.Basket;
import com.cc.springrest.dev.model.Customer;
import com.cc.springrest.dev.model.Order;
import com.cc.springrest.dev.model.OrderItem;
import com.cc.springrest.dev.service.BasketService;
import com.cc.springrest.dev.service.OrderService;
import com.cc.springrest.dev.utils.ResponseConstants;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {
	
	@InjectMocks
	OrderController orderController;
	
	@Mock
	OrderService orderService;
	
	@Mock
	BasketService basketService;
	
	@Test
	public void testCreateOrderInvalidBasketId() {
		
		Long basketId = 1L;
		
		when(basketService.findById(basketId)).thenReturn(null);
		
		ResponseEntity<?> response = orderController.create(1, basketId);
		
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(ResponseConstants.INVALID_BASKET_ID, response.getBody());
	}
	
	@Test
	public void testCreateOrderEmptyBasket() {
		
		Long basketId = 1L;
		Basket basket = new Basket(null, new Customer());
		
		when(basketService.findById(basketId)).thenReturn(basket);
		
		ResponseEntity<?> response = orderController.create(1, basketId);
		
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(ResponseConstants.CANNOT_CREATE_ORDER_BASKET_EMPTY, response.getBody());
	}
	
	@Test
	public void testCreateOrder_OrderAlreadyExistsForBasket() {
		
		Long basketId = 1L;
		
		OrderItem item = new OrderItem();
		Order order = new Order();
		order.setId(9L);
		item.setOrder(order);
		
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		orderItems.add(item);
		
		Basket basket = new Basket(orderItems, new Customer());
		
		when(basketService.findById(basketId)).thenReturn(basket);
		
		ResponseEntity<?> response = orderController.create(1, basketId);
		
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals(ResponseConstants.ORDER_CREATED_ALREADY, response.getBody());
	}
	
	@Test
	public void testCreateOrderIncorrectCustomerId() {
		
		Long basketId = 1L;
		Long customerId = 8L;
		
		OrderItem item = new OrderItem();		
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		orderItems.add(item);
		
		Customer customer = new Customer();
		customer.setId(5L);
		Basket basket = new Basket(orderItems, customer);
		
		when(basketService.findById(basketId)).thenReturn(basket);
		
		ResponseEntity<?> response = orderController.create(customerId, basketId);
		
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals(ResponseConstants.INCORRECT_CUSTOMER_ID, response.getBody());
	}
	
	@Test
	public void testCreateOrderSuccess() {
		
		Long basketId = 1L;
		Long customerId = 8L;
		Long orderId = 10L;
		
		OrderItem item = new OrderItem();		
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		orderItems.add(item);
		
		Customer customer = new Customer();
		customer.setId(customerId);
		Basket basket = new Basket(orderItems, customer);
		
		when(basketService.findById(basketId)).thenReturn(basket);
		when(orderService.createOrder(customer, basket)).thenReturn(orderId);
		ResponseEntity<?> response = orderController.create(customerId, basketId);
		
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(String.format(ResponseConstants.ORDER_CREATED_SUCCESSFULLY, orderId), response.getBody());
	}

	@Test
	public void testGetOrdersForCustomerNoOrderFound() {
		
		Long customerId = 1L;
		
		when(orderService.getOrdersForCustomer(customerId)).thenReturn(null);
		
		ResponseEntity<?> response = orderController.getOrdersForCustomer(customerId);
		
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(ResponseConstants.NO_ORDER_FOUND_FOR_CUSTOMER, response.getBody());
	}
	
	@Test
	public void testGetOrderByIDNoOrderFound() {
		
		Long orderId = 1L;
		
		when(orderService.getOrderById(orderId)).thenReturn(null);
		
		ResponseEntity<?> response = orderController.getOrderById(orderId);
		
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(ResponseConstants.ORDER_NOT_FOUND, response.getBody());
	}
}
