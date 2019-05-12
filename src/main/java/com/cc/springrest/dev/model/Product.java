package com.cc.springrest.dev.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "product")
public class Product implements Serializable {

	private static final long serialVersionUID = 4501753715497967062L;
	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	
	@Column(name="display_name")
	private String displayName;
	
	private double price;

	@JsonIgnore
	@ManyToMany
	private List<Basket> basket;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
