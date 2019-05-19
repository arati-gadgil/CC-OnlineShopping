package com.cc.springrest.dev.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cc.springrest.dev.model.Basket;
import com.cc.springrest.dev.model.Customer;
import com.cc.springrest.dev.model.OrderItem;
import com.cc.springrest.dev.service.BasketService;
import com.cc.springrest.dev.utils.ResponseConstants;

@RestController
@RequestMapping("/basket")
public class BasketController {

	@Autowired
	private BasketService basketService;
	
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Basket basket) {
		
		if (Objects.isNull(basket)) {
			return new ResponseEntity<>(ResponseConstants.EMPTY_REQUEST_PAYLOAD, HttpStatus.BAD_REQUEST);
	    }

	    if (CollectionUtils.isEmpty(basket.getOrderItems())) {
	    	return new ResponseEntity<>(ResponseConstants.NO_ORDER_ITEMS_IN_BASKET, HttpStatus.BAD_REQUEST);
	    }

	    if(Objects.isNull(basket.getCustomer())) {
	    	return new ResponseEntity<>(ResponseConstants.NO_CUSTOMER_FOUND_IN_REQUEST, HttpStatus.BAD_REQUEST);
	    }

	    //Long basketId = basketService.save(new Basket(basket.getOrderItems(), basket.getCustomer()));
	    Long basketId = basketService.save(basket);
	    
	    Map<String, String> responsePayload = new HashMap<>();
	    responsePayload.put(ResponseConstants.STATUS, ResponseConstants.SUCCESS);
	    responsePayload.put(ResponseConstants.RESPONSE, String.format(ResponseConstants.BASKET_CREATED_SUCCESSFULLY, basketId));

	    return new ResponseEntity<>(responsePayload, HttpStatus.CREATED);
	}

	@PutMapping(value = "/{basketId}/add/{customerId}")
	public ResponseEntity<?> addItemToBasket(@PathVariable("basketId") long basketId, @PathVariable("customerId") long customerId,
	      @RequestBody OrderItem orderItem) {

		if(Objects.isNull(orderItem)) {
			return new ResponseEntity<>(ResponseConstants.CANNOT_ADD_EMPTY_ITEM, HttpStatus.BAD_REQUEST);
		}
		
		//check if basket exists
		Basket savedBasket = basketService.findById(basketId);
	    if (null != savedBasket) {
	    	//validate customer id
	    	Customer customer = savedBasket.getCustomer();
	    	if(null != customer && null != customer.getId() && customerId == customer.getId().longValue())  {
	    		Long itemId = basketService.addOrderItem(savedBasket, orderItem);
	    		return new ResponseEntity<>(String.format(ResponseConstants.ITEM_ADDED_TO_BASKET, itemId) , HttpStatus.OK);
	    	} else {
	    		return new ResponseEntity<>(ResponseConstants.INCORRECT_CUSTOMER_ID, HttpStatus.BAD_REQUEST);
	    	}
	    }

	    return new ResponseEntity<>(ResponseConstants.INVALID_BASKET_ID, HttpStatus.NOT_FOUND);
	  }

	@DeleteMapping(value = "/{basketId}/{customerId}/delete/{orderItemIdToRemove}")
	public ResponseEntity<?> removeItemFromBasket(@PathVariable("basketId") long basketId, @PathVariable("customerId") long customerId, 
			@PathVariable long orderItemIdToRemove) {
		
		//check if basket exists
		Basket savedBasket = basketService.findById(basketId);
	    if (null != savedBasket) {
	    	//validate customer id
	    	Customer customer = savedBasket.getCustomer();
	    	if(null != customer && null != customer.getId() && customerId == customer.getId().longValue()) {
	    		OrderItem orderItem = savedBasket.removeOrderItem(orderItemIdToRemove);
	    		
	    		if(Objects.nonNull(orderItem)){
	    			basketService.deleteItem(orderItem);
	    		} else {
	    		    return new ResponseEntity<>(ResponseConstants.INVALID_ITEM_ID, HttpStatus.NOT_FOUND);	
	    		}
	    	} else {
	    		return new ResponseEntity<>(ResponseConstants.INCORRECT_CUSTOMER_ID, HttpStatus.BAD_REQUEST);
	    	}
	     
	      return new ResponseEntity<>(String.format(ResponseConstants.ITEM_REMOVED_FROM_BASKET, orderItemIdToRemove), HttpStatus.OK);
	    }

	    return new ResponseEntity<>(ResponseConstants.INVALID_BASKET_ID, HttpStatus.NOT_FOUND);
	  }
}
