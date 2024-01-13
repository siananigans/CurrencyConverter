package com.app;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class ExchangeRateService {

    private final String ApiKey = "";
    public JSONObject getConversion(String fromCurrency, String toCurrency, String amount) throws URISyntaxException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("http://api.exchangeratesapi.io/v1/convert");
        URI uri = new URIBuilder(request.getURI()).addParameter("from", fromCurrency)
                .addParameter("to", toCurrency)
                .addParameter("amount", amount)
                .addParameter("access_key", this.ApiKey)
                .build();
        request.setURI(uri);
        System.out.println(request.getURI());

        return httpClient.execute(request, new GetHttpClientResponseHandler());
    }

    public Double getExchangeRate(String toCurrency) throws URISyntaxException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("http://api.exchangeratesapi.io/v1/latest");
        URI uri = new URIBuilder(request.getURI())
                .addParameter("access_key", this.ApiKey)
                        .build();
        request.setURI(uri);

        JSONObject jsonResponse = httpClient.execute(request, new GetHttpClientResponseHandler());
        JSONObject jsonRates = jsonResponse.getJSONObject("rates");
        try {
            return jsonRates.getDouble(toCurrency);
        }
        catch (JSONException e) {
            System.out.println("Invalid Currency given as input.");
        }
        return null;
    }
}
