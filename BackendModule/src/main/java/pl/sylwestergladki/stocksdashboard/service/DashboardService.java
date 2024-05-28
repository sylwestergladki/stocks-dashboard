package pl.sylwestergladki.stocksdashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sylwestergladki.stocksdashboard.model.StockDashboard;
import pl.sylwestergladki.stocksdashboard.model.StockData;
import pl.sylwestergladki.stocksdashboard.repository.StockDashboardRepository;
import pl.sylwestergladki.stocksdashboard.stockDataClient.StockDataClient;

import java.util.List;


@Service
public class DashboardService {

    StockDashboard stockDashboard;
    StockDashboardRepository stockDashboardRepository;
    StockDataClient stockDataClient;

    DashboardService(StockDashboard stockDashboard, StockDashboardRepository stockDashboardRepository,
    StockDataClient stockDataClient){
        this.stockDashboard = stockDashboard;
        this.stockDashboardRepository = stockDashboardRepository;
        this.stockDataClient = stockDataClient;
    }

    public void createStockDashboard(String symbol){
        System.out.println("Create dashboard with symbol: " + symbol);
        stockDashboardRepository.save(new StockDashboard(symbol));
    }

    public void deleteStockDashboard(Long id) {
        System.out.println("Delete dashboard with id: " + id);
        stockDashboardRepository.deleteById(id);
    }

    public List<StockDashboard> getAllDashboards() {
        return stockDashboardRepository.findAll();
    }

    public StockData getDashboardData(Long id) {
        StockDashboard dashboard = stockDashboardRepository.getById(id);
        return stockDataClient.getStockData(dashboard.getSymbol());
    }
}
