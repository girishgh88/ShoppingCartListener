package com.demo.shoppingcart;
import static com.demo.shoppingcart.common.constant.ApplicationConstant.MSSAGETYPENAME_TEXT;

import java.math.BigDecimal;

import javax.jms.ConnectionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import com.demo.shoppingcart.dto.ItemDTO;
import com.demo.shoppingcart.producer.MessageProducer;

import lombok.extern.slf4j.Slf4j;

/**
 * Application entry class to initiate spring-boot
 * 
 * @author Girish G H
 */
@SpringBootApplication
@EnableJms
@Slf4j
public class CartListenerApplication implements CommandLineRunner {

	@Autowired
	private MessageProducer messageProducer;

	/**
	 * Entry method to initialize spring-boot
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		log.debug("starting Springboot..");
		SpringApplication.run(CartListenerApplication.class, args);
		log.info("Springboot initiatization completed..");
	}

	/**
	 * Test method to put values to queue
	 */
	@Override
	public void run(String... args) throws Exception {
		log.debug("Start putting Pen to queue");
		messageProducer.produce(new ItemDTO("Pen", BigDecimal.valueOf(10), 5));
		log.debug("Start putting Book to queue");
		messageProducer.produce(new ItemDTO("Book", BigDecimal.valueOf(20), 10));
		log.debug("Start putting Pencil to queue");
		messageProducer.produce(new ItemDTO("Pencil", BigDecimal.valueOf(5), 5));
		log.info("All items added to queue");
	}

	/**
	 * Creating bean {@link JmsListenerContainerFactory}
	 * 
	 * @param connectionFactory
	 * @param configurer
	 * @return {@link DefaultJmsListenerContainerFactory}
	 */
	@Bean
	public JmsListenerContainerFactory<?> jmsListenerContainerFactory(ConnectionFactory connectionFactory,
			DefaultJmsListenerContainerFactoryConfigurer configurer) {
		log.debug("Start creating JmsListenerContainerFactory");
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		configurer.configure(factory, connectionFactory);
		log.debug("JmsListenerContainerFactory created successfully");
		return factory;
	}

	/**
	 * Creating bean {@link MessageConverter}
	 * 
	 * @return {@link MappingJackson2MessageConverter}
	 */
	@Bean
	public MessageConverter jacksonJmsMessageConverter() {
		log.debug("Start creating MessageConverter");
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName(MSSAGETYPENAME_TEXT);
		log.debug("MessageConverter created successfully");
		return converter;
	}
}
