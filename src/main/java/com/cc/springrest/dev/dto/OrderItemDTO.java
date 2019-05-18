package com.cc.springrest.dev.dto;

import java.io.Serializable;

import com.cc.springrest.dev.model.OrderItem;

public class OrderItemDTO implements Serializable {

	private static final long serialVersionUID = -4190533977928167978L;
	
	private String itemId;
	private String itemName;
	private String itemDescription;
	private String itemQuantity;
	private String itemPrice;
	
	public OrderItemDTO(OrderItem orderItem) {
		this.itemId = orderItem.getItemId().toString();
		this.itemName = orderItem.getItemName();
		this.itemDescription = orderItem.getItemDescription();
		this.itemQuantity = orderItem.getItemQuantity().toString();
		this.itemPrice = orderItem.getItemPrice();
	}
	
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public String getItemQuantity() {
		return itemQuantity;
	}
	public void setItemQuantity(String itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	public String getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}
}
