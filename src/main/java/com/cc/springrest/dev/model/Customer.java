package com.cc.springrest.dev.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "customers")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Customer implements Serializable {

	private static final long serialVersionUID = 7014491929940446085L;

	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;

	  @Column(name = "first_name")
	  private String firstName;

	  @Column(name = "last_name")
	  private String lastName;

	  @Column(name = "email_address")
	  private String emailAddress;

	  @Column(name = "date_of_birth")
	  @JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy")
	  private LocalDate dateOfBirth;

	  @Column(name = "orders")
	  @OneToMany(mappedBy = "customer")
	  private List<Order> orders;

	  @JoinColumn(name = "basket")
	  @OneToOne(mappedBy = "customer")
	  private Basket basket;

	  public Customer() {}

	  /**
	   *
	   * @param firstName     first name string
	   * @param lastName      last name
	   * @param emailAddress  customer email address
	   * @param dateOfBirth   customers DoB
	   */
	  public Customer(String firstName, String lastName, String emailAddress,
	      LocalDate dateOfBirth) {
	    this.firstName = firstName;
	    this.lastName = lastName;
	    this.emailAddress = emailAddress;
	    this.dateOfBirth = dateOfBirth;
	  }

	  public Customer(String firstName, String lastName, String emailAddress, LocalDate dateOfBirth, List<Order> orders,
	                  Basket basket) {
	    this(firstName, lastName, emailAddress, dateOfBirth);
	    this.orders = orders;
	    this.basket = basket;
	  }

	  public Long getId() {
	    return id;
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

	  public LocalDate getDateOfBirth() {
	    return dateOfBirth;
	  }

	  public List<Order> getOrders() {
	    return orders;
	  }

	  public Basket getBasket() {
	    return basket;
	  }

	  public void setId(Long id) {
	    this.id = id;
	  }
//	@Id
//	@GeneratedValue
//	private int id;
//	
//	@Column(name="first_name")
//	private String firstName;
//	
//	@Column(name="last_name")
//	private String lastName;
//	
//	private String email;
//	
//	@JsonIgnore
//	@OneToMany
//	private List<Basket> basket;
//
//
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public String getFirstName() {
//		return firstName;
//	}
//
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//
//	public String getLastName() {
//		return lastName;
//	}
//
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
}
