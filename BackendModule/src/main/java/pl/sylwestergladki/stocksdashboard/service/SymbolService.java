package pl.sylwestergladki.stocksdashboard.service;

import pl.sylwestergladki.stocksdashboard.stockDataClient.dto.TickerResponse;

public interface SymbolService {
    TickerResponse getSymbolMatch(String symbol);
}
