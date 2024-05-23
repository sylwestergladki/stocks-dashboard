package pl.sylwestergladki.stocksdashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sylwestergladki.stocksdashboard.model.StockDashboard;
import pl.sylwestergladki.stocksdashboard.repository.StockDashboardRepository;


@Service
public class DashboardService {

    StockDashboard stockDashboard;
    StockDashboardRepository stockDashboardRepository;

    DashboardService(StockDashboard stockDashboard, StockDashboardRepository stockDashboardRepository){
        this.stockDashboard = stockDashboard;
        this.stockDashboardRepository = stockDashboardRepository;
    }

    public void createStockDashboard(String symbol){
        System.out.println("Create dashboard with symbol: " + symbol);
        stockDashboardRepository.save(new StockDashboard(symbol));
    }

}
