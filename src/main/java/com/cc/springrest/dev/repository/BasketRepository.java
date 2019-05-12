package com.cc.springrest.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.cc.springrest.dev.model.Basket;

@Component
public interface BasketRepository extends JpaRepository<Basket, Long> {
}
