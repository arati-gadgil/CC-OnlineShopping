package com.cc.springrest.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cc.springrest.dev.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}