package com.task.bank.DBOperation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.task.bank.Entities.ExchangeRate;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ExchangeRateOperation extends BasicOperations {

    public ExchangeRate[] getExchangesRate() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        URL obj = new URL("https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5");
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        try (InputStream os = conn.getInputStream()) {
            String json = new String(os.readAllBytes(), StandardCharsets.UTF_8);

            return gson.fromJson(json, ExchangeRate[].class);
        }
    }

    public void addExchangeRatesToDB(ExchangeRate[] exchangeRates) {
        addToDB(exchangeRates);
    }

    public void deleteRates(List<ExchangeRate> exchangeRates) {
        removeFromDB(exchangeRates.toArray());
    }

}
