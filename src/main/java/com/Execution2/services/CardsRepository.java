package com.Execution2.services;

import com.Execution2.models.Cards;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CardsRepository extends JpaRepository<Cards, Integer> {

}
