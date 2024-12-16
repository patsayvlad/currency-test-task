package com.example.currencytesttaskspribe.repository;

import com.example.currencytesttaskspribe.model.Currency;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    Optional<Currency> findByName(String name);
}