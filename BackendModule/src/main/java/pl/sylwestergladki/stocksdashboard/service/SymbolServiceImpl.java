package pl.sylwestergladki.stocksdashboard.service;

import org.springframework.stereotype.Service;
import pl.sylwestergladki.stocksdashboard.stockDataClient.StockDataClient;
import pl.sylwestergladki.stocksdashboard.stockDataClient.dto.TickerResponse;

import java.util.regex.Pattern;

@Service
public class SymbolServiceImpl implements SymbolService {

    private StockDataClient stockDataClient;
    private static final Pattern SYMBOL_PATTERN = Pattern.compile("^[A-Z]+$");

    SymbolServiceImpl(StockDataClient stockDataClient){
        this.stockDataClient = stockDataClient;
    }

    public TickerResponse getSymbolMatch(String symbol) {
        if (!SYMBOL_PATTERN.matcher(symbol).matches()) {
            throw new IllegalArgumentException("Invalid stock symbol: " + symbol);
        }
        return stockDataClient.getSymbolInfo(symbol);
    }
}
