package pl.sylwestergladki.stocksdashboard.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sylwestergladki.stocksdashboard.service.SymbolServiceImpl;
import pl.sylwestergladki.stocksdashboard.stockDataClient.dto.TickerResponse;

@RestController
@RequestMapping("/api/symbols")
public class SymbolController {

    private SymbolServiceImpl symbolService;

    public SymbolController(SymbolServiceImpl symbolService){
        this.symbolService = symbolService;
    }

    @GetMapping("/search/{symbol}")
    public ResponseEntity<TickerResponse> getSymbolData(@PathVariable String symbol){
        return ResponseEntity.ok(symbolService.getSymbolMatch(symbol));
    }
}
