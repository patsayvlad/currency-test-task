package com.example.currencytesttaskspribe.controller;

import com.example.currencytesttaskspribe.dto.CurrencyRateDto;
import com.example.currencytesttaskspribe.service.CurrencyService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/currencies")
public class CurrencyController {

  private final CurrencyService currencyService;

  public CurrencyController(CurrencyService currencyService) {
    this.currencyService = currencyService;
  }

  @GetMapping
  public List<CurrencyRateDto> getCurrencies() {
    return currencyService.getCurrencies();
  }

  @GetMapping("/{name}/rate")
  public ResponseEntity<CurrencyRateDto> getExchangeRate(@PathVariable String name) {
    return ResponseEntity.ok(currencyService.getExchangeRate(name));
  }

  @PostMapping
  public ResponseEntity<CurrencyRateDto> addCurrency(@RequestParam String name) {
    return ResponseEntity.ok(currencyService.addCurrency(name));
  }
}