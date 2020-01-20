package com.learn.currencyexchangeservice.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.learn.currencyexchangeservice.model.ExchangeValue;
import com.learn.currencyexchangeservice.repository.CurrencyExchangeRepository;

@RestController
public class CurrencyExchangeServiceController {
	Logger logger = LoggerFactory.getLogger(CurrencyExchangeServiceController.class); 
	@Autowired
	private Environment environment;
	
	@Autowired
	private CurrencyExchangeRepository exchangeRepo;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue fetchConversionRate(@PathVariable String from, @PathVariable String to) {
		
		Optional<ExchangeValue> getExchangeValue = exchangeRepo.findByFromAndTo(from, to);
		
		if(getExchangeValue.isPresent()) {
			ExchangeValue exchangeValue = getExchangeValue.get();
			exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
			return exchangeValue;
		}else {
			logger.error("no such combination of from: '"+ from+"' and to: '"+ to+"' exist in DB");
			return null;
		}
	}
}
