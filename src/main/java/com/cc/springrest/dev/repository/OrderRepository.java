package com.cc.springrest.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cc.springrest.dev.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	  Order findByCustomerId(Long customerId);
	}
