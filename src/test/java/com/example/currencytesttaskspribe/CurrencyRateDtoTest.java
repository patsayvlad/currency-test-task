package com.example.currencytesttaskspribe;

import com.example.currencytesttaskspribe.dto.CurrencyRateDto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;

public class CurrencyRateDtoTest {

    @Test
    void whenDtoInitialized_thenFieldsAreSet() {
        CurrencyRateDto dto = new CurrencyRateDto("USD", BigDecimal.valueOf(1.23));
        assertEquals("USD", dto.getName());
        assertEquals(BigDecimal.valueOf(1.23), dto.getRate());
    }
}