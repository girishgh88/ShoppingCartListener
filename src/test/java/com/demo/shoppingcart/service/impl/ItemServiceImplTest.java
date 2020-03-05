package com.demo.shoppingcart.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.demo.shoppingcart.entity.Item;
import com.demo.shoppingcart.repo.ItemRepo;

/**
 * Test cases for {@link ItemServiceImpl}
 * 
 * @author Girish
 *
 */
@RunWith(MockitoJUnitRunner.class)
class ItemServiceImplTest {

	@InjectMocks
	ItemServiceImpl itemService;

	@Mock
	ItemRepo itemRepo;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Test for save {@link Item} success
	 */
	@Test
	void testSaveSuccess() {
		long testItemId = 1L;
		when(itemRepo.save(any(Item.class))).then(invocation -> {
			Item item = invocation.getArgument(0, Item.class);
			item.setId(testItemId);
			return item;
		});

		Item item = new Item();
		item.setItemCode("Pen01");
		item.setPrice(BigDecimal.valueOf(10));
		item.setQuantity(5);
		item = itemService.save(item);

		verify(itemRepo, times(1)).save(any(Item.class));
		assertNotNull(item);
		assertEquals(item.getId().longValue(), testItemId);
	}

	/**
	 * Test for finding {@link Item} but its itemCode which is existing
	 */
	@Test
	void testGetByItemCodeWithItemFound() {
		when(itemRepo.findByItemCode(anyString())).then(invocation -> {
			String itemCode = invocation.getArgument(0, String.class);
			Item item = null;
			if ("Pen01".equals(itemCode)) {
				item = new Item();
				item.setItemCode("Pen01");
				item.setPrice(BigDecimal.valueOf(10));
				item.setQuantity(5);
			}
			return Optional.ofNullable(item);
		});

		String testItemCode = "Pen01";
		Optional<Item> item = itemService.getByItemCode(testItemCode);

		verify(itemRepo, times(1)).findByItemCode(anyString());
		assertTrue(item.isPresent());
	}

	/**
	 * Test for finding {@link Item} but its itemCode which is not existing
	 */
	@Test
	void testGetByItemCodeWithItemNotFound() {
		when(itemRepo.findByItemCode(anyString())).then(invocation -> {
			String itemCode = invocation.getArgument(0, String.class);
			Item item = null;
			if ("Pen01".equals(itemCode)) {
				item = new Item();
				item.setItemCode("Pen01");
				item.setPrice(BigDecimal.valueOf(10));
				item.setQuantity(5);
			}
			return Optional.ofNullable(item);
		});

		String testItemCode = "Pen02";
		Optional<Item> item = itemService.getByItemCode(testItemCode);
		verify(itemRepo, times(1)).findByItemCode(anyString());
		assertFalse(item.isPresent());
	}
}
