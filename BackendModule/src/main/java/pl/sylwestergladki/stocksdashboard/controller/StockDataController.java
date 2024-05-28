package pl.sylwestergladki.stocksdashboard.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sylwestergladki.stocksdashboard.model.StockData;
import pl.sylwestergladki.stocksdashboard.service.DashboardService;
import pl.sylwestergladki.stocksdashboard.stockDataClient.StockDataClient;

@RestController
@RequestMapping("/stocks")
public class StockDataController {

    private final StockDataClient stockDataService;

    public StockDataController(StockDataClient stockDataService, DashboardService dasboardService) {
        this.stockDataService = stockDataService;
    }

    @GetMapping("/{symbol}")
    public  ResponseEntity<StockData> getStockData(@PathVariable String symbol) {
        StockData stockData = stockDataService.getStockData(symbol);
        if (stockData != null) {
            return ResponseEntity.ok(stockData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }




}
