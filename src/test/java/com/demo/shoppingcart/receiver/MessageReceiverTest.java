package com.demo.shoppingcart.receiver;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.shoppingcart.dto.ItemDTO;
import com.demo.shoppingcart.producer.MessageProducer;

/**
 * Test cases for {@link MessageReceiver} & {@link MessageProducer} with JMS
 * 
 * @author Girish
 *
 */
@SpringBootTest
public class MessageReceiverTest {
	
	@Autowired
    private MessageProducer messageProducer;
	
	/**
	 * Test for JMS send & receive
	 */
	@Test
	void testReceiveMessage() {
		messageProducer.produce(new ItemDTO("Book", BigDecimal.valueOf(20), 10));
	}
}
