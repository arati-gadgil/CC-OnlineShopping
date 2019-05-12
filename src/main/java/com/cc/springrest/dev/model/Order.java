package com.cc.springrest.dev.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "orders")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Order {

	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;

	  @Column(name = "created_on")
	  @JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy")
	  private LocalDate createdOn;

	  @Column(name = "updated_on")
	  @JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy")
	  private LocalDate updatedOn;

	  @Column(name = "items")
	  @OneToMany(mappedBy = "order")
	  private List<OrderItem> items;

	  @JsonIgnore
	  @ManyToOne
	  private Customer customer;

	  public Order() {
	  }

	  public Order(LocalDate createdOn, LocalDate updatedOn, List<OrderItem> items) {
	    this.createdOn = createdOn;
	    this.updatedOn = updatedOn;
	    this.items = items;
	  }

	  public Order(LocalDate createdOn, LocalDate updatedOn, List<OrderItem> items, Customer customer) {
	    this(createdOn, updatedOn, items);
	    this.customer = customer;
	  }

	  public Long getId() {
	    return id;
	  }

	  public LocalDate getCreatedOn() {
	    return createdOn;
	  }

	  public LocalDate getUpdatedOn() {
	    return updatedOn;
	  }

	  public List<OrderItem> getItems() {
	    return items;
	  }

	  public Customer getCustomer() {
	    return customer;
	  }
}
