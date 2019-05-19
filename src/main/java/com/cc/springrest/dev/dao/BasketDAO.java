package com.cc.springrest.dev.dao;

import org.springframework.stereotype.Repository;

import com.cc.springrest.dev.model.Basket;
import com.cc.springrest.dev.model.OrderItem;

@Repository
public class BasketDAO extends BaseDAO {
	public Long save(Basket basket) {
		return (Long) getSession().save(basket);
	}
	
	public Basket findById(Long basketId) {
		return((Basket) getSession().get(Basket.class, basketId));
	}
	
	public Long addOrderItem(OrderItem orderItem) {
		return (Long) getSession().save(orderItem);
	}

	public void deleteItem(OrderItem orderItem) {
		getSession().delete(orderItem);
	}
}
