package com.example.currencytesttaskspribe;

import com.example.currencytesttaskspribe.dto.CurrencyRateDto;
import com.example.currencytesttaskspribe.mapper.CurrencyMapper;
import com.example.currencytesttaskspribe.model.Currency;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class CurrencyMapperTest {

    @Test
    void whenMapToDto_thenCorrectFields() {
        Currency currency = new Currency();
        currency.setName("USD");
        currency.setExchangeRate(BigDecimal.valueOf(1.23));
        currency.setLoggedAt(LocalDateTime.now());

        CurrencyRateDto dto = CurrencyMapper.toDto(currency);
        assertEquals("USD", dto.getName());
        assertEquals(BigDecimal.valueOf(1.23), dto.getRate());
    }
}