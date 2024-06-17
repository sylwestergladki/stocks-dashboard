package pl.sylwestergladki.stocksdashboard.stockDataClient;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import pl.sylwestergladki.stocksdashboard.stockDataClient.dto.StockData;


@Component
public class StockDataClient {

    private final RestTemplate restTemplate;


    public StockDataClient(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public StockData getStockData(String symbol) {
        String apiKey = "example_token";
        String apiUrl = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + symbol + "&interval=5min&apikey=" + apiKey;
        return restTemplate.getForObject(apiUrl, StockData.class);
    }






}

