package com.example.currencytesttaskspribe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CurrencyRateDto {
    private String name;
    private BigDecimal rate;
}