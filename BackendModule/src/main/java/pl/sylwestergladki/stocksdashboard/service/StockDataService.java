package pl.sylwestergladki.stocksdashboard.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pl.sylwestergladki.stocksdashboard.model.StockData;

@Service
public class StockDataService {


    private final String apiKey="YGZ7N89WB20J913J";

    private final RestTemplate restTemplate = new RestTemplate();


    public StockData getStockData(String symbol) {
        String apiUrl = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + symbol + "&interval=5min&apikey=" + apiKey;

        StockData stockData = restTemplate.getForObject(apiUrl, StockData.class);
        System.out.println(restTemplate.getForObject(apiUrl, String.class));
        return stockData;
    }
}

