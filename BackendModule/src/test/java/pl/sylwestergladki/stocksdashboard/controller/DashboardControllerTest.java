package pl.sylwestergladki.stocksdashboard.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.sylwestergladki.stocksdashboard.model.StockDashboard;
import pl.sylwestergladki.stocksdashboard.model.StockInterval;
import pl.sylwestergladki.stocksdashboard.service.StockDashboardServiceImpl;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DashboardControllerTest {

    @Mock
    private StockDashboardServiceImpl stockdashboardService;

    @InjectMocks
    private DashboardController dashboardController;

    String symbol = "AAPL";
    String interval = "DAY";
    String dateFrom = "2023-01-01";
    String dateTo = "2023-12-31";

    @Test
    void DashboardControllerTest_createDashboard_withCorrectParameters_returnsCreatedStatus() {
        ResponseEntity<String> response = dashboardController.createDashboard(symbol, interval, dateFrom, dateTo);

        Assertions.assertThat(HttpStatus.CREATED).isEqualTo(response.getStatusCode());
        Assertions.assertThat("Dashboard created successfully").isEqualTo(response.getBody());
        verify(stockdashboardService, times(1)).createStockDashboard(symbol, StockInterval.DAY,
                LocalDate.parse(dateFrom), LocalDate.parse(dateTo));
    }

    @Test
    void DashboardControllerTest_createDashboard_withInvalidInterval_returnsBadRequestStatus() {
        interval = "INVALID";
        ResponseEntity<String> response = dashboardController.createDashboard(symbol, interval, dateFrom, dateTo);

        Assertions.assertThat(HttpStatus.BAD_REQUEST).isEqualTo(response.getStatusCode());
        Assertions.assertThat("Invalid interval: " + interval).isEqualTo(response.getBody());
        verify(stockdashboardService, never()).createStockDashboard(any(), any(), any(), any());
    }

    @Test
    void DashboardControllerTest_createDashboard_withInvalidDate_returnsBadRequestStatus() {
        String dateFrom = "invalid-date";

        ResponseEntity<String> response = dashboardController.createDashboard(symbol, interval, dateFrom, dateTo);

        Assertions.assertThat(HttpStatus.BAD_REQUEST).isEqualTo(response.getStatusCode());
        Assertions.assertThat("Invalid date format. Use 'yyyy-MM-dd'").isEqualTo(response.getBody());
        verify(stockdashboardService, never()).createStockDashboard(any(), any(), any(), any());
    }

    @Test
    void DashboardControllerTest_deleteDashboard_returnsNoContentStatus() {
        Long dashboardId = 1L;

        ResponseEntity<String> response = dashboardController.deleteDashboard(dashboardId);

        Assertions.assertThat(HttpStatus.NO_CONTENT).isEqualTo(response.getStatusCode());
        verify(stockdashboardService, times(1)).deleteStockDashboard(dashboardId);
    }

    @Test
    void DashboardControllerTest_getAllDashboards_returnsOkStatus() {
        List<StockDashboard> dashboards = Collections.singletonList(StockDashboard.builder().build());
        when(stockdashboardService.getAllDashboards()).thenReturn(dashboards);

        ResponseEntity<List<StockDashboard>> response = dashboardController.getAllDashboards();

        Assertions.assertThat(HttpStatus.OK).isEqualTo(response.getStatusCode());
        Assertions.assertThat(dashboards).isEqualTo(response.getBody());
        verify(stockdashboardService, times(1)).getAllDashboards();
    }
}