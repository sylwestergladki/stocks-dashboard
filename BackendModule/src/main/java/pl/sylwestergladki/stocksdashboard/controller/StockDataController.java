package pl.sylwestergladki.stocksdashboard.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sylwestergladki.stocksdashboard.model.StockData;
import pl.sylwestergladki.stocksdashboard.service.DashboardService;
import pl.sylwestergladki.stocksdashboard.service.StockDataService;

@RestController
@RequestMapping("/stocks")
public class StockDataController {

    private final StockDataService stockDataService;
    private final DashboardService dasboardService;

    public StockDataController(StockDataService stockDataService, DashboardService dasboardService) {
        this.stockDataService = stockDataService;
        this.dasboardService = dasboardService;

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

    @PostMapping("/{symbol}")
    public ResponseEntity<String> makeDashboard(@PathVariable String symbol) {
        dasboardService.createStockDashboard(symbol);
        return new ResponseEntity<>("Dashboard created successfully", HttpStatus.CREATED);
    }



}
