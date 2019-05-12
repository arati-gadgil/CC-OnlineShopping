package com.cc.springrest.dev.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.cc.springrest.dev.model.Product;

@Repository
public class ProductDAO extends BaseDAO {

	@SuppressWarnings("unchecked")
	public List<Product> getAllProducts() {	
		return getSession().createCriteria(Product.class).list();

	}
	
	@SuppressWarnings("unchecked")
	public Product getProductById(int productId) {	
		List<Product> products = getSession().createCriteria(Product.class).add(Restrictions.eq("id", productId)).list();
		if(! CollectionUtils.isEmpty(products)) {
			return products.get(0);
		}
		
		return null;
	}
}
