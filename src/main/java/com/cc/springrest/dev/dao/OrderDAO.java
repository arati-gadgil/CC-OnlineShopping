package com.cc.springrest.dev.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cc.springrest.dev.model.Order;

@Repository
public class OrderDAO extends BaseDAO {

	public Long createNewOrder(Order order) {
		//getSession().saveOrUpdate(order);
		return (Long) getSession().save(order);
	}
	
	@SuppressWarnings("unchecked")
	public List<Order> getOrdersForCustomer(Long customerId){
		return getSession().createCriteria(Order.class).add(Restrictions.eq("customer.id", customerId)).list();
	}

	public Order getOrderById(Long orderId) {
		return (Order) getSession().get(Order.class, orderId);
	}
}
