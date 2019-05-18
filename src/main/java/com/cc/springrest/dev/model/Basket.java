package com.cc.springrest.dev.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.util.CollectionUtils;


@Entity
@Table(name = "baskets")
public class Basket implements Serializable {

	private static final long serialVersionUID = 1690319703310233295L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "order_items")
	@OneToMany(mappedBy = "basket", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<OrderItem> orderItems;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="customer_id")
	private Customer customer;

	public Basket() {}

	public Basket(List<OrderItem> orderItems, Customer customer) {
		for (OrderItem orderItem: orderItems) {
	    	orderItem.setBasket(this);
	    }
	    this.orderItems = orderItems;
	    this.customer = customer;
	  }

	  public Long getId() {
	    return id;
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
			  item.setBasket(this);
			  this.orderItems.add(item);
		  }
	 }

	public OrderItem removeOrderItem(long orderItemIdToRemove) {
		if(! CollectionUtils.isEmpty(this.orderItems)) {
			for(OrderItem orderItem : this.orderItems) {
				if(orderItem.getItemId().longValue() == orderItemIdToRemove) {
					this.orderItems.remove(orderItem);
					return orderItem;
				}
			}
		}
		return null;
	}
}
