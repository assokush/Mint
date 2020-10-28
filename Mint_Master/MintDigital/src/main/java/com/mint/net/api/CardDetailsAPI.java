package com.mint.net.api;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.web.bind.annotation.*;

import com.mint.net.entities.CardDetailsEntity;
import com.mint.net.model.CardDetailsModel;
import com.mint.net.service.CardDetailsService;



@RestController
@RequestMapping("card-scheme")
@EnableKafka
public class CardDetailsAPI {

    private static Logger logger = LogManager.getLogger(CardDetailsAPI.class);

    private CardDetailsService cardDetailsService;

    @Autowired
    public CardDetailsAPI(CardDetailsService cardDetailsService) {
        this.cardDetailsService = cardDetailsService;
    }

    @GetMapping("/verify/{bin}")
    @ResponseBody
    public CardDetailsModel verifyCardScheme(@PathVariable String bin){
       
       logger.info("Bin ==> " + bin );

        return cardDetailsService.verifyCardScheme(bin);

    }


    @GetMapping("/stats/")
    public  Page<CardDetailsEntity> getHitStatistics(@RequestParam(name = "start") Integer start, @RequestParam ( name = "limit")Integer limit){
     Pageable page = null;
        logger.info("Hit starts here ...... ");
        return cardDetailsService.getCardHitStatistics(page);


    }
    
    


}
