package com.demo.shoppingcart.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.shoppingcart.entity.Item;

/**
 * Repository abstraction for {@link Item}.
 * 
 * @author Girish G H
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
public interface ItemRepo extends JpaRepository<Item, Long> {

	Optional<Item> findByItemCode(String itemCode);
}