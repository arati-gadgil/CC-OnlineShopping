package com.cc.springrest.dev.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cc.springrest.dev.dao.OrderDAO;
import com.cc.springrest.dev.dto.OrderDTO;
import com.cc.springrest.dev.model.Basket;
import com.cc.springrest.dev.model.Customer;
import com.cc.springrest.dev.model.Order;

@Service
@Transactional
public class OrderService {
	
	@Autowired
	OrderDAO orderDAO;
	
	public Long createOrder(Customer customer, Basket basket) {
		
		Order order = new Order(customer, basket.getOrderItems());
		return orderDAO.createNewOrder(order);
	}
	
	public List<OrderDTO> getOrdersForCustomer(long customerId) {
		List<Order> orders = orderDAO.getOrdersForCustomer(Long.valueOf(customerId));
		
		if(CollectionUtils.isEmpty(orders)) {
			return null;
		}
		
		List<OrderDTO> orderList = new ArrayList<OrderDTO>();
		for(Order order: orders) {
			OrderDTO orderDTO = new OrderDTO(order);
			orderList.add(orderDTO);
		}
		return orderList;
	}

	public OrderDTO getOrderById(Long orderId) {
		
		Order order = orderDAO.getOrderById(Long.valueOf(orderId));
		if(Objects.isNull(order)) {
			return null;
		}
		return new OrderDTO(order);
	}
}
