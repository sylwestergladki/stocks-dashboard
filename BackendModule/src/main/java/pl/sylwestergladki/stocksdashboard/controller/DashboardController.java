package pl.sylwestergladki.stocksdashboard.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.sylwestergladki.stocksdashboard.model.StockDashboard;
import pl.sylwestergladki.stocksdashboard.service.StockDashboardServiceImpl;


import java.util.List;

@Controller
@RequestMapping("/api/dashboards")
public class DashboardController {

    private final StockDashboardServiceImpl dashboardService;

    public DashboardController(StockDashboardServiceImpl dasboardService) {
        this.dashboardService = dasboardService;
    }

    @PostMapping("/{symbol}")
    public ResponseEntity<String> createDashboard(@PathVariable String symbol) {
        dashboardService.createStockDashboard(symbol);
        return new ResponseEntity<>("Dashboard created successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDashboard(@PathVariable Long id){
        dashboardService.deleteStockDashboard(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<StockDashboard>> getAllDashboards() {
        return ResponseEntity.ok(dashboardService.getAllDashboards());
    }






}
