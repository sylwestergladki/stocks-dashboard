package pl.sylwestergladki.stocksdashboard.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import pl.sylwestergladki.stocksdashboard.helpers.DateHelper;
import pl.sylwestergladki.stocksdashboard.model.StockDashboard;
import pl.sylwestergladki.stocksdashboard.repository.StockDashboardRepository;
import pl.sylwestergladki.stocksdashboard.stockDataClient.StockDataClient;
import pl.sylwestergladki.stocksdashboard.stockDataClient.dto.ResultDto;
import pl.sylwestergladki.stocksdashboard.stockDataClient.dto.StockDto;


import java.time.LocalDate;
import java.util.*;


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

        public StockDashboard createStockDashboard(String symbol, LocalDate dateFrom, LocalDate dateTo){
            if(dateTo.isBefore(dateFrom)){
                throw new IllegalArgumentException("DateTo is before dateFrom");
            }

            Optional<StockDto> stockData = stockDataClient.getStockData(symbol, dateFrom, dateTo);
            if(stockData.isEmpty()){
                throw new IllegalArgumentException("There is no valor with this symbol: " + symbol);
            }

            StockDashboard dashboard = new StockDashboard();
            dashboard.setSymbol(symbol);


            Map<LocalDate, ResultDto> stockPriceInfo = new HashMap<>();
            for (int i = 0; i < stockData.get().getResultsCount() -1; i++) {
                stockPriceInfo.put(DateHelper.convertTimestampToLocalDate(stockData.get().getResults().get(i).getT()),
                        stockData.get().getResults().get(i));
            }

            dashboard.setStockPriceInfo(stockPriceInfo);
            return stockDashboardRepository.save(dashboard);
    }

    public void deleteStockDashboard(Long id) {
        stockDashboardRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("StockDashboard not found with id: " + id));

        stockDashboardRepository.deleteById(id);
    }

    public List<StockDashboard> getAllDashboards() {
        return stockDashboardRepository.findAll();
    }

}
