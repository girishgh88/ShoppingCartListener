package com.demo.shoppingcart.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO to map item details
 * 
 * @author Girish G H
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4597939706213177120L;

	private String itemCode;
	private BigDecimal price;
	private Integer quantity;
}
