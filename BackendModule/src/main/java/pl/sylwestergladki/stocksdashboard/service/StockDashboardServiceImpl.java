package pl.sylwestergladki.stocksdashboard.service;

import org.springframework.stereotype.Service;
import pl.sylwestergladki.stocksdashboard.model.StockDashboard;
import pl.sylwestergladki.stocksdashboard.repository.StockDashboardRepository;
import pl.sylwestergladki.stocksdashboard.stockDataClient.StockDataClient;
import pl.sylwestergladki.stocksdashboard.stockDataClient.dto.StockData;


import java.util.List;


@Service
public class StockDashboardServiceImpl implements StockDashboardService {

    StockDashboard stockDashboard;
    StockDashboardRepository stockDashboardRepository;
    StockDataClient stockDataClient;

    StockDashboardServiceImpl(StockDashboard stockDashboard, StockDashboardRepository stockDashboardRepository,
                              StockDataClient stockDataClient){
        this.stockDashboard = stockDashboard;
        this.stockDashboardRepository = stockDashboardRepository;
        this.stockDataClient = stockDataClient;
    }

    public void createStockDashboard(String symbol){
        StockData stockData = stockDataClient.getStockData(symbol);
        StockDashboard dashboard = new StockDashboard();
        dashboard.setSymbol(symbol);
        dashboard.setStockPriceInfo(stockData.getStockPriceInfo());
        stockDashboardRepository.save(dashboard);
    }

    public void deleteStockDashboard(Long id) {
        System.out.println("Delete dashboard with id: " + id);
        stockDashboardRepository.deleteById(id);
    }

    public List<StockDashboard> getAllDashboards() {
        return stockDashboardRepository.findAll();
    }

}
