package pl.sylwestergladki.stocksdashboard.stockDataClient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import pl.sylwestergladki.stocksdashboard.model.StockInterval;
import pl.sylwestergladki.stocksdashboard.stockDataClient.dto.StockDto;
import pl.sylwestergladki.stocksdashboard.stockDataClient.dto.TickerResponse;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StockDataClientTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private StockDataClient stockDataClient;

    @Value("${api.key}")
    private String apiKey;

    @BeforeEach
    void setUp() {
        stockDataClient.apiKey = apiKey;
    }

    @Test
    void StockDataClient_getStockData_returnsStockDto() {
        String symbol = "AAPL";
        StockInterval interval = StockInterval.DAY;
        LocalDate dateFrom = LocalDate.of(2023, 1, 1);
        LocalDate dateTo = LocalDate.of(2023, 1, 31);

        StockDto mockResponse = StockDto.builder().build(); // Create a mock StockDto
        String expectedUrl = "https://api.polygon.io/v2/aggs/ticker/" +
                symbol + "/range/1/" +
                interval.getValue() + "/" +
                dateFrom + "/" +
                dateTo + "/?adjusted=true&sort=asc&apiKey=" +
                apiKey;

        when(restTemplate.getForObject(expectedUrl, StockDto.class)).thenReturn(mockResponse);

        Optional<StockDto> result = stockDataClient.getStockData(symbol, interval, dateFrom, dateTo);

        assertTrue(result.isPresent());
        assertEquals(mockResponse, result.get());

        verify(restTemplate).getForObject(expectedUrl, StockDto.class);
    }

    @Test
    void StockDataClientTest_getSymbolInfo_returnsTickerResponse() {
        String symbol = "AAPL";
        TickerResponse mockResponse = TickerResponse.builder().build();

        String expectedUrl = "https://api.polygon.io/v3/reference/tickers?market=stocks&search=" +
                symbol +
                "&active=true&limit=100&apiKey=" +
                apiKey;

        when(restTemplate.getForObject(expectedUrl, TickerResponse.class)).thenReturn(mockResponse);

        TickerResponse result = stockDataClient.getSymbolInfo(symbol);

        assertNotNull(result);
        assertEquals(mockResponse, result);

        verify(restTemplate).getForObject(expectedUrl, TickerResponse.class);
    }
}
