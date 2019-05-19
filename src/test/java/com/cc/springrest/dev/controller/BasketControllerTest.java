package com.cc.springrest.dev.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cc.springrest.dev.model.Basket;
import com.cc.springrest.dev.model.Customer;
import com.cc.springrest.dev.model.OrderItem;
import com.cc.springrest.dev.service.BasketService;
import com.cc.springrest.dev.utils.ResponseConstants;

@RunWith(MockitoJUnitRunner.class)
public class BasketControllerTest {

	@Mock
	Basket basket;
	
	@Mock
	OrderItem orderItem;
	
	@InjectMocks
	BasketController basketController;
	
	@Mock
	BasketService basketService;
	
	@Test
	public void testCreateBasketThrowErrorWithNullBasket() {
		
		ResponseEntity<?> response = basketController.create(null);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals(ResponseConstants.EMPTY_REQUEST_PAYLOAD, response.getBody());
	}
	
	@Test
	public void testCreateBasketThrowErrorWithNoOrderItems() {
		
		ResponseEntity<?> response = basketController.create(basket);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals(ResponseConstants.NO_ORDER_ITEMS_IN_BASKET, response.getBody());
	}
	
	@Test
	public void testCreateBasketThrowErrorWithNoCustomer() {
		
		Basket basket = new Basket();
		basket.addOrderItem(orderItem);
		
		ResponseEntity<?> response = basketController.create(basket);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals(ResponseConstants.NO_CUSTOMER_FOUND_IN_REQUEST, response.getBody());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testCreateBasketSuccessful() {
		
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		orderItems.add(new OrderItem());
		Basket basket = new Basket(orderItems, new Customer());
		
		when(basketService.save(basket)).thenReturn(Long.valueOf("1"));
		ResponseEntity<?> response = basketController.create(basket);
		
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		Map<String, String> responsePayload = (Map<String, String>) response.getBody();
		
		assertEquals(ResponseConstants.SUCCESS, responsePayload.get(ResponseConstants.STATUS));
		assertEquals(String.format(ResponseConstants.BASKET_CREATED_SUCCESSFULLY, 1), responsePayload.get(ResponseConstants.RESPONSE));
	}
	
	@Test
	public void testAddItemThrowErrorOnNullItem() {
	
		ResponseEntity<?> response = basketController.addItemToBasket(1, 2, null);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals(ResponseConstants.CANNOT_ADD_EMPTY_ITEM, response.getBody());
	}
	
	@Test
	public void testAddItemThrowErrorOnInvalidBasketID() {
	
		Long basketId = 1L;
		when(basketService.findById(basketId)).thenReturn(null);
		
		ResponseEntity<?> response = basketController.addItemToBasket(basketId, 2, orderItem);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(ResponseConstants.INVALID_BASKET_ID, response.getBody());
	}
	
	@Test
	public void testAddItemThrowErrorOnIncorrectCustomerID() {
	
		Long customerId = 2L;
		Long basketId = 1L;
		Customer customer = new Customer();
		customer.setId(3L);
		Basket basket = new Basket(null, customer);
		
		when(basketService.findById(basketId)).thenReturn(basket);
		
		ResponseEntity<?> response = basketController.addItemToBasket(basketId, customerId, orderItem);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals(ResponseConstants.INCORRECT_CUSTOMER_ID, response.getBody());
	}
	
	@Test
	public void testAddItemSuccess() {
	
		Long customerId = 2L;
		Long basketId = 1L;
		Customer customer = new Customer();
		customer.setId(customerId);
		Basket basket = new Basket(null, customer);
		
		when(basketService.findById(basketId)).thenReturn(basket);
		when(basketService.addOrderItem(basket, orderItem)).thenReturn(4L);
		
		ResponseEntity<?> response = basketController.addItemToBasket(basketId, customerId, orderItem);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(String.format(ResponseConstants.ITEM_ADDED_TO_BASKET, 4L), response.getBody());
	}
	
	@Test
	public void testRemoveItemThrowErrorOnInvalidBasketID() {
	
		Long basketId = 1L;
		when(basketService.findById(basketId)).thenReturn(null);
		
		ResponseEntity<?> response = basketController.removeItemFromBasket(basketId, 1, 1);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(ResponseConstants.INVALID_BASKET_ID, response.getBody());
	}
	
	@Test
	public void testRemoveItemThrowErrorOnIncorrectCustomerID() {
	
		Long customerId = 2L;
		Long basketId = 1L;
		Customer customer = new Customer();
		customer.setId(3L);
		Basket basket = new Basket(null, customer);
		
		when(basketService.findById(basketId)).thenReturn(basket);
		
		ResponseEntity<?> response = basketController.removeItemFromBasket(basketId, customerId, 1);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals(ResponseConstants.INCORRECT_CUSTOMER_ID, response.getBody());
	}
	
	@Test
	public void testRemoveItemThrowErrorOnInvalidItemID() {
	
		Long customerId = 2L;
		Long basketId = 1L;
		Long itemId = 4L;
		Customer customer = new Customer();
		customer.setId(customerId);
		OrderItem item = new OrderItem();
		item.setItemId(7L);
		List<OrderItem> itemList = new ArrayList<OrderItem>();
		itemList.add(item);
		Basket basket = new Basket(itemList, customer);
		
		when(basketService.findById(basketId)).thenReturn(basket);
		
		ResponseEntity<?> response = basketController.removeItemFromBasket(basketId, customerId, itemId);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(ResponseConstants.INVALID_ITEM_ID, response.getBody());
	}
	
	@Test
	public void testRemoveItemSuccess() {
	
		Long customerId = 2L;
		Long basketId = 1L;
		Long itemId = 4L;
		Customer customer = new Customer();
		customer.setId(customerId);
		OrderItem item = new OrderItem();
		item.setItemId(itemId);
		List<OrderItem> itemList = new ArrayList<OrderItem>();
		itemList.add(item);
		Basket basket = new Basket(itemList, customer);
		
		when(basketService.findById(basketId)).thenReturn(basket);
		
		ResponseEntity<?> response = basketController.removeItemFromBasket(basketId, customerId, itemId);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(String.format(ResponseConstants.ITEM_REMOVED_FROM_BASKET, itemId), response.getBody());
	}
}
