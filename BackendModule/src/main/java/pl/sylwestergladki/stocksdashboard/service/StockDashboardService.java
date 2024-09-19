package pl.sylwestergladki.stocksdashboard.service;

import pl.sylwestergladki.stocksdashboard.model.StockDashboard;

import java.time.LocalDate;
import java.util.List;

public interface StockDashboardService {
    StockDashboard createStockDashboard(String symbol, LocalDate dateFrom, LocalDate dateTo);
    void deleteStockDashboard(Long id);
    List<StockDashboard> getAllDashboards();
}
