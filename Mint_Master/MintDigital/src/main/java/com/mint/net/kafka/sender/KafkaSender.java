package com.mint.net.kafka.sender;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.mint.net.model.CardDetailsModel;

@Component
public class KafkaSender {
	@Value("${kafka-topic}")
	private String topic1;

	private final Logger LOG = LoggerFactory.getLogger(KafkaSender.class);

	//@Autowired
	//private RoutingKafkaTemplate kafkaTemplate;
	
	//@Autowired
	//private KafkaTemplate kafkaTemplates;
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessage(CardDetailsModel cardDetailsModel) {
		LOG.info("Sending With Message Converter : {}", cardDetailsModel);
		LOG.info("--------------------------------");

	
		 Message<CardDetailsModel> message = MessageBuilder
	                .withPayload(cardDetailsModel)
	                .setHeader(KafkaHeaders.TOPIC, topic1)
	                .build();
	        
	        kafkaTemplate.send(message);
	 
	}

}
