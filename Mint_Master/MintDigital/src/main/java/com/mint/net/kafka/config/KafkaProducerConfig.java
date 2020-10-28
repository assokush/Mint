package com.mint.net.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.mint.net.model.CardDetailsModel;


@Configuration
public class KafkaProducerConfig {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@Value("${kafka-server}")
	private String bootstrapServers;


	@Bean
	KafkaTemplate<String, String> kafkaTemplate() {
		KafkaTemplate<String, String> kafkaTemplate = new KafkaTemplate<>(producerFactory());
		kafkaTemplate.setMessageConverter(new StringJsonMessageConverter());
		kafkaTemplate.setDefaultTopic("topic1");
		kafkaTemplate.setProducerListener(new ProducerListener<String, String>() {
			@Override
			public void onSuccess(ProducerRecord<String, String> producerRecord, RecordMetadata recordMetadata) {
				LOG.info("ACK from ProducerListener message: {} offset:  {}", producerRecord.value(),
						recordMetadata.offset());
			}
		});
		return kafkaTemplate;
	}

	@Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,  bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }
 
	
	
	
	@Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "json");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return props;
    }

    @Bean
    public ConsumerFactory<String, CardDetailsModel> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(),new StringDeserializer(),new JsonDeserializer<>(CardDetailsModel.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CardDetailsModel> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, CardDetailsModel> factory =new ConcurrentKafkaListenerContainerFactory<>();
        
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
  
}