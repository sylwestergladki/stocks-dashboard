package pl.sylwestergladki.stocksdashboard.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.sylwestergladki.stocksdashboard.model.StockDashboard;
import pl.sylwestergladki.stocksdashboard.service.StockDashboardServiceImpl;

    import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@Controller
@RequestMapping("/api/dashboards")
public class DashboardController {

    private final StockDashboardServiceImpl stockdashboardService;

    private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);


    public DashboardController(StockDashboardServiceImpl dasboardService) {
        this.stockdashboardService = dasboardService;
    }

    @PostMapping("/create-dashboard")
    public ResponseEntity<String> createDashboard(@RequestParam String symbol,
                                                  @RequestParam String dateFrom,
                                                  @RequestParam String dateTo) {
        logger.info("Received request to create dashboard with symbol: {}, dateFrom: {}, dateTo: {}",
                symbol, dateFrom, dateTo);

        LocalDate fromDate;
        LocalDate toDate;
        try {
            fromDate = LocalDate.parse(dateFrom);
            toDate = LocalDate.parse(dateTo);
        } catch (DateTimeParseException e) {
            return new ResponseEntity<>("Invalid date format. Use 'yyyy-MM-dd'", HttpStatus.BAD_REQUEST);
        }
        try {
            stockdashboardService.createStockDashboard(symbol, fromDate, toDate);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Dashboard created successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDashboard(@PathVariable Long id){
        stockdashboardService.deleteStockDashboard(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<StockDashboard>> getAllDashboards() {
        return ResponseEntity.ok(stockdashboardService.getAllDashboards());
    }

}
