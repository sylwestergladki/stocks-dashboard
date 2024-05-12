package pl.sylwestergladki.stocksdashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sylwestergladki.stocksdashboard.model.StockData;
import pl.sylwestergladki.stocksdashboard.service.StockDataService;

@RestController
@RequestMapping("/stocks")
public class StockDataController {

    private final StockDataService stockDataService;

    @Autowired
    public StockDataController(StockDataService stockDataService) {
        this.stockDataService = stockDataService;
    }

    @GetMapping("/{symbol}")
    public  ResponseEntity<StockData> getStockData(@PathVariable String symbol) {
        StockData stockData = stockDataService.getStockData(symbol);
        System.out.println(stockData.getStockMetaData().getSymbol());
        if (stockData != null) {
            return ResponseEntity.ok(stockData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
