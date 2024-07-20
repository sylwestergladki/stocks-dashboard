package pl.sylwestergladki.stocksdashboard.stockDataClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import pl.sylwestergladki.stocksdashboard.model.StockInterval;
import pl.sylwestergladki.stocksdashboard.stockDataClient.dto.TickerResponse;
import pl.sylwestergladki.stocksdashboard.stockDataClient.dto.StockDto;

import java.time.LocalDate;
import java.util.Optional;


@Component
public class StockDataClient {

    private final RestTemplate restTemplate;
    @Value("${api.key}")
    String apiKey;


    public StockDataClient(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public Optional<StockDto> getStockData(String symbol, StockInterval interval, LocalDate dateFrom, LocalDate dateTo) {

        String apiUrl = "https://api.polygon.io/v2/aggs/ticker/" +
                symbol +
                "/range/1/" +
                interval.getValue() +
                "/" + dateFrom +
                "/" + dateTo + "/" +
                "?adjusted=true&sort=asc&apiKey=" +
                apiKey;


         return Optional.of( restTemplate.getForObject(apiUrl, StockDto.class));
    }


    public TickerResponse getSymbolInfo(String symbol) {
        String url = "https://api.polygon.io/v3/reference/tickers?market=stocks&search=" +
                symbol +
                "&active=true&limit=100&apiKey=" +
                apiKey;

        return restTemplate.getForObject(url, TickerResponse.class);
    }

}

