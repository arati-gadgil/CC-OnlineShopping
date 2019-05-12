package com.cc.springrest.dev.dao;

import org.springframework.stereotype.Repository;

import com.cc.springrest.dev.model.Basket;

@Repository
public class BasketDAO extends BaseDAO {

	public void createNewBasket(Basket basket) {
		getSession().save(basket);
	}
	
	public Long save(Basket basket) {
		return (Long) getSession().save(basket);
	}
}
