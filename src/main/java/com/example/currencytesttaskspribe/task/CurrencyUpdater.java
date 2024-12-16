package com.example.currencytesttaskspribe.task;

import com.example.currencytesttaskspribe.service.CurrencyService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class CurrencyUpdater {

    private final CurrencyService currencyService;

    public CurrencyUpdater(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @PostConstruct
    public void updateRatesAtStartup() {
        log.debug("Updating rates at startup...");
        currencyService.updateExchangeRates();
    }

    @Scheduled(fixedRate = 3600000)
    public void updateRates() {
        log.debug("Scheduled update of exchange rates...");
        currencyService.updateExchangeRates();
    }
}
