package com.app;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException {
        double amount = Double.parseDouble(args[0]);
        String toCurrency = args[1];
        ExchangeRateService exchangeRateService = new ExchangeRateService();
        Double exchangeRate = exchangeRateService.getExchangeRate(toCurrency);
        if (exchangeRate != null) {
            Double newAmount = amount * exchangeRate;
            System.out.println("Amount in " + toCurrency + " is:");
            System.out.println(newAmount + " " + toCurrency);
        }
    }
}
