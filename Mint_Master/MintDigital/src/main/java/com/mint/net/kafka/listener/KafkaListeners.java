package com.mint.net.kafka.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.mint.net.model.CardDetailsModel;

@Component
public class KafkaListeners {
	
	private final Logger LOG = LoggerFactory.getLogger(KafkaListeners.class);

	
	@KafkaListener(topics = "com.ng.vela.even.card_verified",   groupId="default", containerFactory="kafkaListenerContainerFactory")
		  void listener(CardDetailsModel model) {
		    LOG.info("CustomUserListener [{}]", model.getPayload().getScheme() +"  "+model.getPayload().getBank() );
		  }

}
