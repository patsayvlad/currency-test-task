package com.example.currencytesttaskspribe;

import com.example.currencytesttaskspribe.model.Currency;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CurrencyTest {

    @Test
    void testCurrencyModel() {
        Currency currency = new Currency();
        currency.setId(1L);
        currency.setName("USD");
        currency.setExchangeRate(BigDecimal.valueOf(1.23));
        currency.setLoggedAt(LocalDateTime.now());

        assertEquals(1L, currency.getId());
        assertEquals("USD", currency.getName());
        assertEquals(BigDecimal.valueOf(1.23), currency.getExchangeRate());
        assertNotNull(currency.getLoggedAt());
    }
}