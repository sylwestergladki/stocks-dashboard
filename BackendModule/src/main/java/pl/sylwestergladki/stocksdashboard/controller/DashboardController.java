package pl.sylwestergladki.stocksdashboard.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.sylwestergladki.stocksdashboard.model.StockDashboard;
import pl.sylwestergladki.stocksdashboard.model.StockData;
import pl.sylwestergladki.stocksdashboard.service.DashboardService;

import java.util.List;

@Controller
@RequestMapping("/dashboards")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dasboardService) {
        this.dashboardService = dasboardService;
    }

    @PostMapping("/{symbol}")
    public ResponseEntity<String> makeDashboard(@PathVariable String symbol) {
        dashboardService.createStockDashboard(symbol);
        return new ResponseEntity<>("Dashboard created successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDashboard(@PathVariable Long id){
        dashboardService.deleteStockDashboard(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<StockDashboard>> getAllStockDashboards() {
        return ResponseEntity.ok(dashboardService.getAllDashboards());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockData> getStockData(@PathVariable Long id){
        return ResponseEntity.ok(dashboardService.getDashboardData(id));
    }



}
