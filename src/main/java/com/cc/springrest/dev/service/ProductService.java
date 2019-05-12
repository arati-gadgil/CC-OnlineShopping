package com.cc.springrest.dev.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cc.springrest.dev.dao.ProductDAO;
import com.cc.springrest.dev.model.Product;

@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductDAO productDAO;
	
	public List<Product> getAllProducts() {
		
		List<Product> products = productDAO.getAllProducts();
		return products;
	}
	
}
