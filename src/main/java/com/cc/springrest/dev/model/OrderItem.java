package com.cc.springrest.dev.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Embeddable
@Table(name = "order_items")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OrderItem {
	
	@Id
	  @Column(name = "item_id")
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long itemId;

	  @Column(name = "item_quantity")
	  private Integer itemQuantity;

	  @Column(name = "item_name")
	  private String itemName;

	  @Column(name = "item_sku")
	  private String itemSku;

	  @Column(name = "item_description")
	  private String itemDescription;

	  @Column(name = "item_price")
	  private String itemPrice;

	  @Column(name = "date_of_purchase")
	  @JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy")
	  private LocalDate dateOfPurchase;

	  //@JsonIgnore
	  @ManyToOne
	  private Order order;

	  //@JsonIgnore
	  @ManyToOne
	  private Basket basket;

	  public OrderItem() {
	    //lets be at peace with Jackson :-)
	  }

	  public OrderItem(Integer itemQuantity, String itemName, String itemSku,
	      String itemDescription, String itemPrice, LocalDate dateOfPurchase) {
	    this.itemQuantity = itemQuantity;
	    this.itemName = itemName;
	    this.itemSku = itemSku;
	    this.itemDescription = itemDescription;
	    this.itemPrice = itemPrice;
	    this.dateOfPurchase = dateOfPurchase;
	  }

	  public Long getItemId() {
	    return itemId;
	  }

	  public void setItemId(Long itemId) {
	    this.itemId = itemId;
	  }

	  public Integer getItemQuantity() {
	    return itemQuantity;
	  }

	  public void setItemQuantity(Integer itemQuantity) {
	    this.itemQuantity = itemQuantity;
	  }

	  public String getItemName() {
	    return itemName;
	  }

	  public void setItemName(String itemName) {
	    this.itemName = itemName;
	  }

	  public String getItemSku() {
	    return itemSku;
	  }

	  public void setItemSku(String itemSku) {
	    this.itemSku = itemSku;
	  }

	  public String getItemDescription() {
	    return itemDescription;
	  }

	  public void setItemDescription(String itemDescription) {
	    this.itemDescription = itemDescription;
	  }

	  public String getItemPrice() {
	    return itemPrice;
	  }

	  public void setItemPrice(String itemPrice) {
	    this.itemPrice = itemPrice;
	  }

	  public LocalDate getDateOfPurchase() {
	    return dateOfPurchase;
	  }

	  public void setDateOfPurchase(LocalDate dateOfPurchase) {
	    this.dateOfPurchase = dateOfPurchase;
	  }

}
