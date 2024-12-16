package com.example.currencytesttaskspribe;

import com.example.currencytesttaskspribe.dto.CurrencyRateDto;
import com.example.currencytesttaskspribe.model.Currency;
import com.example.currencytesttaskspribe.repository.CurrencyRepository;
import com.example.currencytesttaskspribe.service.CurrencyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CurrencyServiceTest {

    @Mock
    private CurrencyRepository currencyRepository;

    @InjectMocks
    private CurrencyService currencyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenAddCurrency_thenCurrencyIsSavedAndReturnedAsDto() {
        when(currencyRepository.save(any(Currency.class))).thenAnswer(invocation -> invocation.getArgument(0));

        CurrencyRateDto dto = currencyService.addCurrency("UAE");
        assertNotNull(dto);
        assertEquals("UAE", dto.getName());
        assertNotNull(dto.getRate());
        verify(currencyRepository, times(1)).save(any(Currency.class));
    }

    @Test
    void whenGetExchangeRate_thenReturnCorrectDto() {

        Currency currency = new Currency();
        currency.setName("USD");
        currency.setExchangeRate(BigDecimal.valueOf(1.00));

        when(currencyRepository.save(any(Currency.class))).thenReturn(currency);
        currencyService.addCurrency("USD");

        CurrencyRateDto dto = currencyService.getExchangeRate("USD");
        assertNotNull(dto);
        assertEquals("USD", dto.getName());
        assertNotNull(dto.getRate());
    }

    @Test
    void whenGetExchangeRateForNonexistentCurrency_thenThrowException() {
        when(currencyRepository.findByName("XYZ")).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> currencyService.getExchangeRate("XYZ"));

        assertEquals("Currency XYZ not found.", exception.getMessage());
    }

    @Test
    void whenGetCurrencies_thenReturnAllAsDtos() {
        List<CurrencyRateDto> dtos = currencyService.getCurrencies();

        assertNotNull(dtos);
    }

    @Test
    void whenUpdateExchangeRates_thenRatesAreUpdatedInDbAndMemory() {

        Currency first_currency = new Currency();
        first_currency.setName("USD");
        first_currency.setExchangeRate(BigDecimal.valueOf(1.00));

        when(currencyRepository.save(any(Currency.class))).thenReturn(first_currency);

        currencyService.addCurrency("USD");

        Currency currency = new Currency();
        currency.setName("USD");
        currency.setExchangeRate(BigDecimal.valueOf(1.00));

        when(currencyRepository.findByName("USD")).thenReturn(Optional.of(currency));
        when(currencyRepository.save(any(Currency.class))).thenAnswer(invocation -> invocation.getArgument(0));

        currencyService.updateExchangeRates();

        verify(currencyRepository, times(2)).save(any(Currency.class));
    }
}