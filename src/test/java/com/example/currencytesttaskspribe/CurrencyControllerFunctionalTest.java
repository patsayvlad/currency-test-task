package com.example.currencytesttaskspribe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.currencytesttaskspribe.dto.CurrencyRateDto;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CurrencyControllerFunctionalTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void whenGetCurrencies_thenReturnCurrencyList() {
        ResponseEntity<CurrencyRateDto[]> response =
            restTemplate.getForEntity("/api/v1/currencies", CurrencyRateDto[].class);

        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);
    }

    @Test
    void whenGetExchangeRate_thenReturnRateDto() {
        ResponseEntity<CurrencyRateDto> response =
            restTemplate.getForEntity("/api/v1/currencies/USD/rate", CurrencyRateDto.class);

        assertNotNull(response.getBody());
        assertEquals("USD", response.getBody().getName());
        assertTrue(response.getBody().getRate().compareTo(BigDecimal.ZERO) > 0);
    }

    @Test
    void whenGetExchangeRateForNonexistentCurrency_thenThrowException() {
        ResponseEntity<String> response =
            restTemplate.getForEntity("/api/v1/currencies/XYZ/rate", String.class);

        assertNotNull(response);
        assertTrue(response.getBody().contains("Currency XYZ not found."));
    }

    @Test
    void whenAddCurrency_thenCurrencyIsCreated() {
        ResponseEntity<CurrencyRateDto> response =
            restTemplate.postForEntity("/api/v1/currencies?name=ZLT", null, CurrencyRateDto.class);

        assertNotNull(response.getBody());
        assertEquals("ZLT", response.getBody().getName());
    }

    @Test
    void whenAddCurrencyExistentCurrency_thenThrowException() {
        restTemplate.postForEntity("/api/v1/currencies?name=USD", null, String.class);

        ResponseEntity<String> response =
            restTemplate.postForEntity("/api/v1/currencies?name=USD", null, String.class);

        assertNotNull(response);
        assertTrue(response.getBody().contains("Currency USD already exists."));
    }
}