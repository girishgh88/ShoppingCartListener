package com.demo.shoppingcart.producer;

import static com.demo.shoppingcart.common.constant.ApplicationConstant.JMS_ENDPOINT_ITEMDETAILS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.demo.shoppingcart.dto.ItemDTO;

import lombok.extern.slf4j.Slf4j;

/**
 * Component class for message producer
 * 
 * @author Girish G H
 *
 */
@Component
@Slf4j
public class MessageProducer {

	@Autowired
	private JmsTemplate jmsTemplate;

	/**
	 * To put {@link ItemDTO} to queue
	 * 
	 * @param itemDTO
	 */
	public void produce(ItemDTO itemDTO) {
		log.debug("Start putting item to queue: {}", itemDTO);
		jmsTemplate.convertAndSend(JMS_ENDPOINT_ITEMDETAILS, itemDTO);
		log.info("Item added to queue");
	}
}
