package com.mint.net.service;  

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.mint.net.entities.CardDetailsEntity;
import com.mint.net.model.Payload;
import org.springframework.data.domain.Page;

@Component
public interface CustomerService {
		
	public Payload findById(int theId);	
	public Page<CardDetailsEntity> findAll(Pageable pageable);
}
