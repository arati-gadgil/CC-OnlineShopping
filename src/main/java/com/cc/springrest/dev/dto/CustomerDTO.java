package com.cc.springrest.dev.dto;

import java.io.Serializable;

import com.cc.springrest.dev.model.Customer;

public class CustomerDTO implements Serializable {

	private static final long serialVersionUID = 5843866880040888429L;
	
	private String customerId;
	private String firstName;
	private String lastName;
	private String emailAddress;
	
	public CustomerDTO(Customer customer) {
		this.customerId = customer.getId().toString();
		this.firstName = customer.getFirstName();
		this.lastName = customer.getLastName();
		this.emailAddress = customer.getEmailAddress();
	}
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public String getCustomerName() {
		StringBuilder builder = new StringBuilder("");
		builder.append(this.firstName);
		
		if(! builder.toString().equals("")) {
			builder.append(" ");
		}
		
		builder.append(this.lastName);
		return builder.toString();
	}
}
