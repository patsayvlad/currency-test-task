package com.example.currencytesttaskspribe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CurrencyTestTaskSpribeApplication {

  public static void main(String[] args) {
    SpringApplication.run(CurrencyTestTaskSpribeApplication.class, args);
  }

}
