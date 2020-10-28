package com.mint.net.service;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mint.net.dto.card_details.CardDetailsDto;
import com.mint.net.entities.CardDetailsEntity;
import com.mint.net.kafka.sender.KafkaSender;
import com.mint.net.model.CardDetailsModel;
import com.mint.net.model.Payload;
import com.mint.net.repository.CardDetailsRepo;

import java.util.Optional;

@Service
public class CardDetailsService {
    private static Logger logger = LogManager.getLogger(CardDetailsService.class);
    private CardDetailsRepo cardDetailsRepo;

    private RestTemplate restTemplate;
    @Autowired
	private KafkaSender sender;

    
    @Autowired
    public CardDetailsService(CardDetailsRepo cardDetailsRepo, RestTemplate restTemplate) {
        this.cardDetailsRepo = cardDetailsRepo;
        this.restTemplate =restTemplate;
    }



    
    @Value("${binlist.url}")
    private String binlist_url;
    
    @Value("${kafka-topic")
	private String topic1;

    @Value("${connection_timeout}")
    private int connection_timeout;

  

    public CardDetailsModel verifyCardScheme(String bin) {
       
        Optional<CardDetailsEntity> cardDetailsEntity = cardDetailsRepo.findByBin(bin);
       
        ResponseEntity<CardDetailsEntity> cardResponseEntity =restTemplate.getForEntity(binlist_url + "/" + bin, CardDetailsEntity.class);
        cardDetailsRepo.save(cardDetailsEntity.orElseGet(() ->cardResponseEntity.getBody()));
        logger.info("cardResponseEntity "+cardResponseEntity.getBody().getBin());
        
        CardDetailsModel  model= buildCardDetailsModel(cardDetailsEntity.orElseGet(() -> {
            ResponseEntity<CardDetailsDto> responseEntity = restTemplate.getForEntity(binlist_url+ "/" +bin, CardDetailsDto.class);
            return saveCardDetails(responseEntity.getBody(), bin);
        }), bin);

        sender.sendMessage(model);
        
        
       
        return model;

    }


    public Page getCardHitStatistics(Pageable pageable){


       return  cardDetailsRepo.findAll(pageable);

        /**
         *  Am not sure where to get data from, the binlist api does not provide any endpoint supporting this requirement 
         */
    	
    	
    }

    private CardDetailsEntity saveCardDetails(CardDetailsDto cardDetailsDto, String bin) {
       
    	CardDetailsEntity cardDetailsEntity = new CardDetailsEntity();
        cardDetailsEntity.setBankName(cardDetailsDto.getBank() != null ? cardDetailsDto.getBank().getName() : "");
        cardDetailsEntity.setCardScheme(cardDetailsDto.getScheme() != null ? cardDetailsDto.getScheme() : "");
        cardDetailsEntity.setCardType(cardDetailsDto.getType() != null ? cardDetailsDto.getType() : "");
        cardDetailsEntity.setSuccess(true);
        cardDetailsEntity.setBin(bin);

        return cardDetailsRepo.save(cardDetailsEntity);



    }


    private CardDetailsModel buildCardDetailsModel(CardDetailsEntity cardDetailsEntity, String bin) {

        CardDetailsModel cardDetailsModel = new CardDetailsModel();

        cardDetailsModel.setBin(bin);
        cardDetailsModel.setSuccess(cardDetailsEntity.isSuccess());

        Payload payload = new Payload();
        payload.setType(cardDetailsEntity.getCardType());
        payload.setScheme(cardDetailsEntity.getCardScheme());
        payload.setBank(cardDetailsEntity.getBankName());

        cardDetailsModel.setPayload(payload);

        return cardDetailsModel;
    }




}
