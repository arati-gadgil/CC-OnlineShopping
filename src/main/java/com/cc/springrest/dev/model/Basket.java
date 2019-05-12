package com.cc.springrest.dev.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;


@Entity
@Table(name = "baskets")
public class Basket implements Serializable {

	private static final long serialVersionUID = 1690319703310233295L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "created_at")
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate createdAt;

	@Column(name = "updated_at")
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate updatedAt;

	  @Column(name = "order_items")
	  @OneToMany(mappedBy = "basket", cascade = CascadeType.ALL)
	  private List<OrderItem> orderItems;

	  //@JsonIgnore
	  @OneToOne
	  private Customer customer;

	  public Basket() {}

	  public Basket(LocalDate createdAt, LocalDate updatedAt, List<OrderItem> orderItems) {
	    this.createdAt = createdAt;
	    this.updatedAt = updatedAt;
	    this.orderItems = orderItems;
	  }

	  public Basket(LocalDate createdAt, LocalDate updatedAt, List<OrderItem> orderItems, Customer customer) {
	    this(createdAt, updatedAt, orderItems);
	    this.customer = customer;
	  }

	  public Long getId() {
	    return id;
	  }

	  public LocalDate getCreatedAt() {
	    return createdAt;
	  }

	  public LocalDate getUpdatedAt() {
	    return updatedAt;
	  }

	  public void setUpdatedAt(LocalDate updatedAt) {
	    this.updatedAt = updatedAt;
	  }

	  public List<OrderItem> getOrderItems() {
	    return orderItems;
	  }

	  public Customer getCustomer() {
	    return customer;
	  }

	  public void addOrderItem(OrderItem item) {
	    if (CollectionUtils.isEmpty(this.orderItems)) {
	      this.orderItems = new ArrayList<>();
	    }

	    if (Objects.nonNull(item)) {
	      this.orderItems.add(item);
	    }
	  }
}
