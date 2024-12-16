package com.example.currencytesttaskspribe.service;

import com.example.currencytesttaskspribe.dto.CurrencyRateDto;
import com.example.currencytesttaskspribe.mapper.CurrencyMapper;
import com.example.currencytesttaskspribe.model.Currency;
import com.example.currencytesttaskspribe.repository.CurrencyRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final Map<String, BigDecimal> exchangeRates = new ConcurrentHashMap<>();

    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
//        this.currencyRepository.findAll().forEach(currency ->
//            exchangeRates.put(currency.getName(), currency.getExchangeRate()));
//        if (exchangeRates.isEmpty()) {
//            exchangeRates.put("USD",
//                BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(0.5, 2.0)));
//            exchangeRates.put("EUR",
//                BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(0.5, 2.0)));
//            exchangeRates.put("GBP",
//                BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(0.5, 2.0)));
//            exchangeRates.put("JPY",
//                BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(0.5, 2.0)));
//        }
    }

    public List<CurrencyRateDto> getCurrencies() {
        return exchangeRates.entrySet().stream()
            .map(entry -> new CurrencyRateDto(entry.getKey(), entry.getValue()))
            .collect(Collectors.toList());
    }

    public CurrencyRateDto getExchangeRate(String currencyName) {
        return exchangeRates.entrySet().stream()
            .filter(entry -> entry.getKey().equalsIgnoreCase(currencyName))
            .map(entry -> new CurrencyRateDto(entry.getKey(), entry.getValue()))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Currency " + currencyName + " not found."));
    }

    public CurrencyRateDto addCurrency(String name) {
        if (exchangeRates.containsKey(name.toUpperCase())) {
            throw new RuntimeException("Currency " + name + " already exists.");
        }

        Currency currency = new Currency();
        currency.setName(name.toUpperCase());
        currency.setExchangeRate(
            BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(0.5, 2.0)));
        currency.setLoggedAt(LocalDateTime.now());
        currency = currencyRepository.save(currency);
        exchangeRates.put(currency.getName(), currency.getExchangeRate());
        return CurrencyMapper.toDto(currency);
    }

    public void updateExchangeRates() {
        LocalDateTime now = LocalDateTime.now();

        exchangeRates.forEach((name, rate) -> {
            Currency currency = currencyRepository.findByName(name).orElse(new Currency());
            currency.setName(name);
            currency.setExchangeRate(
                BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(0.5, 2.0)));
            currency.setLoggedAt(now);
            currencyRepository.save(currency);
            exchangeRates.put(name, currency.getExchangeRate());
        });
    }
}