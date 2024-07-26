package pl.sylwestergladki.stocksdashboard.service;

import pl.sylwestergladki.stocksdashboard.model.StockDashboard;
import pl.sylwestergladki.stocksdashboard.model.StockInterval;

import java.time.LocalDate;
import java.util.List;

public interface StockDashboardService {
    StockDashboard createStockDashboard(String symbol, StockInterval interval, LocalDate dateFrom, LocalDate dateTo);
    void deleteStockDashboard(Long id);
    List<StockDashboard> getAllDashboards();
}
