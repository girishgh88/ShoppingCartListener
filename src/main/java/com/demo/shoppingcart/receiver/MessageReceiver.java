package com.demo.shoppingcart.receiver;

import static com.demo.shoppingcart.common.constant.ApplicationConstant.JMS_ENDPOINT_ITEMDETAILS;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.demo.shoppingcart.dto.ItemDTO;
import com.demo.shoppingcart.entity.Item;
import com.demo.shoppingcart.service.ItemService;

import lombok.extern.slf4j.Slf4j;

/**
 * Component class for message receiver
 * 
 * @author Girish G H
 *
 */
@Component
@Slf4j
public class MessageReceiver {

	@Autowired
	private ItemService itemService;

	/**
	 * JSM listener method to receive cart item details from queue
	 * @param itemDTO
	 */
	@JmsListener(destination = JMS_ENDPOINT_ITEMDETAILS)
	public void receiveMessage(ItemDTO itemDTO) {
		log.info("Item received : {}", itemDTO);
		Item item = new Item();
		BeanUtils.copyProperties(itemDTO, item);
		log.debug("Entity intialized: {}",item);
		Item savedItem = itemService.save(item);
		log.info("Saved Item : {}", savedItem);
	}
}
