package com.demo.shoppingcart.service;

import java.util.Optional;

import com.demo.shoppingcart.entity.Item;

/**
 * Service abstraction for {@link Item}
 * 
 * @author Girish G H
 *
 */
public interface ItemService {

	/**
	 * To save {@link Item}
	 * 
	 * @param item
	 * @return saved instance of {@link Item}
	 */
	Item save(Item item);

	/**
	 * To retrieve item details based on its itemCode
	 * 
	 * @param itemCode
	 * @return {@link Optional}.of({@link Item})
	 */
	Optional<Item> getByItemCode(String itemCode);
}
