package com.cc.springrest.dev.utils;

public class ResponseConstants {

	public static final String EMPTY_REQUEST_PAYLOAD = "Request payload is empty.";
	
	public static final String EMPTY_PRODUCT_LIST = "No products found.";
	
	public static final String EMPTY_CUSTOMER = "Customer cannot be null";
	
	public static final String SUCCESS = "success";
	
	public static final String STATUS = "status";
	
	public static final String RESPONSE = "response message";
	
	public static final String NO_ORDER_ITEMS_IN_BASKET = "Cannot create a basket without order items.";
	
	public static final String NO_CUSTOMER_FOUND_IN_REQUEST = "No customer found in request";
	
	public static final String CANNOT_ADD_EMPTY_ITEM = "Cannot add empty item to basket.";
	
	public static final String INVALID_BASKET_ID = "Invalid basket id - basket not found";
	
	public static final String INCORRECT_CUSTOMER_ID = "Incorrect customer id sent";
	
	public static final String ITEM_ADDED_TO_BASKET = "Item added to the basket. ID: %d";
	
	public static final String INVALID_ITEM_ID = "Invalid item id - item not found in the basket";
	
	public static final String ITEM_REMOVED_FROM_BASKET = "Item with ID %d removed from basket";
	
	public static final String BASKET_CREATED_SUCCESSFULLY = "Successfully created basket ID - %1$s";
	
	public static final String CANNOT_CREATE_ORDER_BASKET_EMPTY = "Order cannot be created for empty basket";
	
	public static final String ORDER_CREATED_ALREADY = "Order already created for this basket";
	
	public static final String ORDER_CREATED_SUCCESSFULLY = "Successfully created new order with ID : %d";
	
	public static final String NO_ORDER_FOUND_FOR_CUSTOMER = "No orders found for this customer";
	
	public static final String ORDER_NOT_FOUND = "Order Not Found";
}
