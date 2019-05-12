package com.cc.springrest.dev.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cc.springrest.dev.model.Product;

@Repository
public class ProductDAO {

	@Autowired
	private SessionFactory factory;

	@SuppressWarnings("unchecked")
	public List<Product> getAllProducts() {	
		return getSession().createCriteria(Product.class).list();

	}

	private Session getSession() {
		Session session = null;
		try {
			session = factory.getCurrentSession();
		} catch (HibernateException ex) {
			session = factory.openSession();
		}
		return session;
	}
}
