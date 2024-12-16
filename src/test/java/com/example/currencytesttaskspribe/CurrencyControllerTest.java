package com.example.currencytesttaskspribe;

import com.example.currencytesttaskspribe.controller.CurrencyController;
import com.example.currencytesttaskspribe.dto.CurrencyRateDto;
import com.example.currencytesttaskspribe.service.CurrencyService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.math.BigDecimal;
import java.util.List;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CurrencyControllerTest {

    @Mock
    private CurrencyService currencyService;

    @InjectMocks
    private CurrencyController currencyController;

    @Autowired
    private MockMvc mockMvc;

    public CurrencyControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCurrencies() throws Exception {
        CurrencyRateDto currencyRateDto = new CurrencyRateDto("USD", BigDecimal.valueOf(1.23));

        when(currencyService.getCurrencies()).thenReturn(List.of(currencyRateDto));

        mockMvc.perform(get("/api/v1/currencies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").exists());
    }

    @Test
    void testAddCurrency() throws Exception {
        CurrencyRateDto currency = new CurrencyRateDto("USD", BigDecimal.valueOf(1.23));
        when(currencyService.addCurrency("UAH")).thenReturn(currency);

        mockMvc.perform(post("/api/v1/currencies?name=UAH"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("UAH"));
    }
}