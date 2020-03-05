package com.demo.shoppingcart.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

/**
 * Entity for item details
 * 
 * @author Girish G H
 */
@Entity
@Data
public class Item {
	@Id
	@GeneratedValue
	private Long id;
	private String itemCode;
	private BigDecimal price;
	private Integer quantity;
}
