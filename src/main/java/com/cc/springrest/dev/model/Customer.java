package com.cc.springrest.dev.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer implements Serializable {

	private static final long serialVersionUID = 7014491929940446085L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_name", updatable=false)
	private String firstName;

	@Column(name = "last_name", updatable=false)
	private String lastName;

	@Column(name = "email_address", updatable=false)
	private String emailAddress;

	@Column(name = "orders")
	@OneToMany(mappedBy = "customer")
	private List<Order> orders;

	@OneToMany(mappedBy = "customer")
	private List<Basket> baskets;

	public Customer() {}

	public Customer(String firstName, String lastName, String emailAddress) {
	    this.firstName = firstName;
	    this.lastName = lastName;
	    this.emailAddress = emailAddress;
	}

	public Customer(String firstName, String lastName, String emailAddress, List<Order> orders, List<Basket> baskets) {
	    this(firstName, lastName, emailAddress);
	    this.orders = orders;
	    this.baskets = baskets;
	}
	 
	public Long getId() {
	    return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFirstName() {
	    return firstName;
	}

	public String getLastName() {
	    return lastName;
	}

	public String getEmailAddress() {
	    return emailAddress;
	}

	public List<Order> getOrders() {
	    return orders;
	}

	public List<Basket> getBaskets() {
		return baskets;
	}

	public void setBaskets(List<Basket> baskets) {
		this.baskets = baskets;
	}
}
