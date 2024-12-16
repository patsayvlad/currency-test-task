package com.example.currencytesttaskspribe;

import com.example.currencytesttaskspribe.service.CurrencyService;
import com.example.currencytesttaskspribe.task.CurrencyUpdater;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class CurrencyUpdaterTest {

    @Mock
    private CurrencyService currencyService;

    @InjectMocks
    private CurrencyUpdater currencyUpdater;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenApplicationStarts_thenRatesAreUpdated() {
        currencyUpdater.updateRatesAtStartup();
        verify(currencyService, times(1)).updateExchangeRates();
    }

    @Test
    void whenScheduledTaskRuns_thenRatesAreUpdated() {
        currencyUpdater.updateRates();
        verify(currencyService, times(1)).updateExchangeRates();
    }
}