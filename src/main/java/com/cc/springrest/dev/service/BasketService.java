package com.cc.springrest.dev.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cc.springrest.dev.dao.BasketDAO;
import com.cc.springrest.dev.model.Basket;
import com.cc.springrest.dev.model.OrderItem;

@Service
@Transactional
public class BasketService {

	@Autowired
	private BasketDAO basketDAO;
	
	public void createNewBasket(Basket basket) {
		basketDAO.createNewBasket(basket);
	}
	
	public Long save(Basket basket) {
		return basketDAO.save(basket);
	}
	
	public Long addOrderItem(Basket basket, OrderItem orderItem) {
		orderItem.setBasket(basket);
		return basketDAO.addOrderItem(orderItem);
	}
	
	public Basket findById(Long basketId) {
		return basketDAO.findById(basketId);
	}

	public void deleteItem(OrderItem orderItem) {
		basketDAO.deleteItem(orderItem);
	}
}
