package com.learn.currencyexchangeservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.currencyexchangeservice.model.ExchangeValue;

public interface CurrencyExchangeRepository extends JpaRepository<ExchangeValue, Long>{

	Optional<ExchangeValue> findByFromAndTo(String from, String to);

}
