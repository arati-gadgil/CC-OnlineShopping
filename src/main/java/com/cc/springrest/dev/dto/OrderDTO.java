package com.cc.springrest.dev.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.util.CollectionUtils;

import com.cc.springrest.dev.model.Customer;
import com.cc.springrest.dev.model.Order;
import com.cc.springrest.dev.model.OrderItem;

public class OrderDTO implements Serializable {
	
	private static final long serialVersionUID = 1069410483235981031L;
	
	private String orderId;
	private String customerId;
	private String customerName;
	private List<OrderItemDTO> orderItems;
	
	public OrderDTO(Order order) {
		this.orderId = order.getId().toString();
		
		Customer customer = order.getCustomer();
		if(Objects.nonNull(customer)) {
			this.customerId = customer.getId().toString();
			this.customerName = customer.getFirstName() + " " + customer.getLastName();
		}
		
		if (!CollectionUtils.isEmpty(order.getOrderItems())) {
			List<OrderItemDTO> orderItems = new ArrayList<OrderItemDTO>();
			for (OrderItem orderItem : order.getOrderItems()) {
				OrderItemDTO orderItemDTO = new OrderItemDTO(orderItem);
				orderItems.add(orderItemDTO);
			}
			
			this.orderItems = orderItems;
		}
	}
			
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public List<OrderItemDTO> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItemDTO> orderItems) {
		this.orderItems = orderItems;
	}
}
