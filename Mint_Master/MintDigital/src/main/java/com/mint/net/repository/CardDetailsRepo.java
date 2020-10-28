package com.mint.net.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mint.net.entities.CardDetailsEntity;

import java.util.Optional;

public interface CardDetailsRepo extends CrudRepository<CardDetailsEntity, Long> {

    Optional<CardDetailsEntity> findByBin(String bin);

    Page<CardDetailsEntity> findAll(Pageable pageable);
}
