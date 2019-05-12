package com.cc.springrest.dev.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cc.springrest.dev.dao.BasketDAO;
import com.cc.springrest.dev.dao.ProductDAO;
import com.cc.springrest.dev.model.Basket;

@Service
@Transactional
public class BasketService {

	@Autowired
	private BasketDAO basketDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	public void createNewBasket(Basket basket) {
		
//		for(Product product: basket.getProducts()) {
//			product = productDAO.getProductById(product.getId());
//		}
		
		basketDAO.createNewBasket(basket);
	}
	
	public Long save(Basket basket) {
		return basketDAO.save(basket);
	}
}
