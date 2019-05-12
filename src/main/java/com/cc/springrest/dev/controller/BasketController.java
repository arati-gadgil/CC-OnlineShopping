package com.cc.springrest.dev.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cc.springrest.dev.model.Basket;
import com.cc.springrest.dev.service.BasketService;
import com.cc.springrest.dev.utils.ResponseConstants;

@RestController
@RequestMapping("/basket")
public class BasketController {

	@Autowired
	//private BasketRepository basketRepository;
	private BasketService basketService;
	
	
	@PostMapping
	 public ResponseEntity<?> create(@RequestBody Basket basket) {
	    if (Objects.isNull(basket)) {
	      return new ResponseEntity<>(ResponseConstants.EMPTY_REQUEST_PAYLOAD, HttpStatus.BAD_REQUEST);
	    }

	    if (CollectionUtils.isEmpty(basket.getOrderItems())) {
	      return new ResponseEntity<>("Cannot create a basket without order items.", HttpStatus.BAD_REQUEST);
	    }

	    //Basket savedBasket;\
	    Long basketId;

	    if (Objects.nonNull(basket.getCustomer())) {
	    	basketId = basketService.save(new Basket(basket.getCreatedAt(), basket.getUpdatedAt(), basket.getOrderItems(), basket.getCustomer()));
	    } else {
	    	basketId = basketService.save(new Basket(basket.getCreatedAt(), basket.getUpdatedAt(), basket.getOrderItems()));
	    }

	    Map<String, String> responsePayload = new HashMap<>();
	    responsePayload.put(ResponseConstants.STATUS, ResponseConstants.SUCCESS);
	    responsePayload.put(ResponseConstants.RESPONSE, String.format("Successfully created basket %1$s", basketId));

	    return new ResponseEntity<>(responsePayload, HttpStatus.CREATED);
	  }

	
//	@PostMapping
//	public ResponseEntity<?> create(@RequestBody Basket basket) {
//		if (Objects.isNull(basket)) {
//			return new ResponseEntity<>(ResponseConstants.EMPTY_REQUEST_PAYLOAD, HttpStatus.BAD_REQUEST);
//		}
//		
//		if (CollectionUtils.isEmpty(basket.getProducts())) {
//			return new ResponseEntity<>(ResponseConstants.EMPTY_PRODUCT_LIST, HttpStatus.BAD_REQUEST);
//		}
//		
//		if(Objects.isNull(basket.getCustomer())) {
//			return new ResponseEntity<>(ResponseConstants.EMPTY_CUSTOMER, HttpStatus.BAD_REQUEST);
//		}
//		
//		basketService.createNewBasket(basket);
//		
//		Map<String, String> responsePayload = new HashMap<>();
//	    responsePayload.put(ResponseConstants.STATUS, ResponseConstants.SUCCESS);
//	    responsePayload.put(ResponseConstants.RESPONSE, String.format("Successfully created basket %1$s", basket.getId()));
//	    
//	    return new ResponseEntity<>(responsePayload, HttpStatus.CREATED);
//	}
}
