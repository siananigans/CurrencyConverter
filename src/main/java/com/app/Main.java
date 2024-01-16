package com.app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException {

        // Read config
        Properties prop = new Properties();
        String fileName = "config.cfg";
        try (FileInputStream fis = new FileInputStream(fileName)) {
            prop.load(fis);
        } catch (FileNotFoundException ex) {
            System.out.println("Config file not present.");
            throw ex;
        } catch (IOException ex) {
            System.out.println("Error reading config file.");
            throw ex;
        }

        // Set ApiKey for exchange rate service
        String apiKey = prop.getProperty("apikey");
        ExchangeRateService exchangeRateService = new ExchangeRateService();
        exchangeRateService.setApiKey(apiKey);

        // Read in cmd line arguments
        double amount = Double.parseDouble(args[0]);
        String toCurrency = args[1];

        // Get exchange rate for given currency
        Double exchangeRate = exchangeRateService.getExchangeRate(toCurrency);
        if (exchangeRate != null) {
            // Multiply the amount by the exchange rate
            Double newAmount = amount * exchangeRate;
            System.out.println(amount + " Euro in " + toCurrency + " is:");
            System.out.println(newAmount + " " + toCurrency);
        }
    }
}
