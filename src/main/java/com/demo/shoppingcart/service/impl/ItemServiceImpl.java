package com.demo.shoppingcart.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.shoppingcart.entity.Item;
import com.demo.shoppingcart.repo.ItemRepo;
import com.demo.shoppingcart.service.ItemService;

import lombok.extern.slf4j.Slf4j;

/**
 * Implementation for {@link ItemService}
 * 
 * @author Girish G H
 *
 */
@Service
@Slf4j
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepo itemRepo;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Item save(Item item) {
		log.debug("Sending item to repo - {}", item);
		return itemRepo.save(item);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<Item> getByItemCode(String itemCode) {
		log.debug("Finding item from repo for Item {}", itemCode);
		Optional<Item> itemRef = itemRepo.findByItemCode(itemCode);
		log.debug("Lookup status for Item with itemCode {} is {}", itemCode, itemRef.isPresent());
		return itemRef;
	}

}
