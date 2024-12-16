package com.example.currencytesttaskspribe.mapper;

import com.example.currencytesttaskspribe.dto.CurrencyRateDto;
import com.example.currencytesttaskspribe.model.Currency;

public class CurrencyMapper {
    public static CurrencyRateDto toDto(Currency currency) {
        return new CurrencyRateDto(currency.getName(), currency.getExchangeRate());
    }
}