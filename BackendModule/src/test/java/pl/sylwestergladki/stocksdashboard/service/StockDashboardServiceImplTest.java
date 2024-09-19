package pl.sylwestergladki.stocksdashboard.service;

import jakarta.persistence.EntityNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.sylwestergladki.stocksdashboard.model.StockDashboard;
import pl.sylwestergladki.stocksdashboard.repository.StockDashboardRepository;
import pl.sylwestergladki.stocksdashboard.stockDataClient.StockDataClient;
import pl.sylwestergladki.stocksdashboard.stockDataClient.dto.StockDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StockDashboardServiceImplTest {

    @Mock
    private StockDashboardRepository stockDashboardRepository;
    @Mock
    private StockDataClient stockDataClient;

    @InjectMocks
    private StockDashboardServiceImpl stockDashboardService;

    private final String SYMBOL = "AAPL";
    private final LocalDate DATE_FROM = LocalDate.of(2023, 1, 1);
    private final LocalDate DATE_TO = LocalDate.of(2023, 1, 10);
    private final Long DASHBOARD_ID = 1L;


    @Test
    public void stockDashboardService_createStockDashboard_stockDToIsNotEmpty_returnStockDashboard(){
        StockDto stockDto = StockDto.builder().build();
        Optional<StockDto> optionalStockDto = Optional.of(stockDto);

        when(stockDataClient.getStockData(SYMBOL, DATE_FROM, DATE_TO)).thenReturn(optionalStockDto);
        when(stockDashboardRepository.save(any(StockDashboard.class))).thenAnswer(invocation -> invocation.getArgument(0));

        StockDashboard dashboard = stockDashboardService.createStockDashboard(SYMBOL, DATE_FROM, DATE_TO);

        Assertions.assertThat(dashboard).isNotNull();
        Assertions.assertThat(dashboard.getSymbol()).isEqualTo(SYMBOL);

        verify(stockDataClient, times(1)).getStockData(SYMBOL, DATE_FROM, DATE_TO);
        verify(stockDashboardRepository, times(1)).save(any(StockDashboard.class));
    }

    @Test
    public void stockDashboardService_createStockDashboard_stockDToIsEmpty_ThrowsException() {

        when(stockDataClient.getStockData(SYMBOL, DATE_FROM, DATE_TO)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            stockDashboardService.createStockDashboard(SYMBOL, DATE_FROM, DATE_TO);
        });

        Assertions.assertThat("There is no valor with this symbol: " + SYMBOL).isEqualTo(exception.getMessage());

        verify(stockDataClient, times(1)).getStockData(SYMBOL, DATE_FROM, DATE_TO);
        verify(stockDashboardRepository, times(0)).save(any(StockDashboard.class));
    }

    @Test
    public void stockDashboardService_createStockDashboard_dateToIsBeforeDateFrom_ThrowsException() {
        LocalDate dateTo = LocalDate.of(2023, 1,1);
        LocalDate dateFrom = LocalDate.of(2023, 2,1);
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            stockDashboardService.createStockDashboard(SYMBOL, dateFrom, dateTo);
        });

        Assertions.assertThat("DateTo is before dateFrom").isEqualTo(exception.getMessage());

        verify(stockDataClient, times(0)).getStockData(SYMBOL, dateFrom, dateTo);
        verify(stockDashboardRepository, times(0)).save(any(StockDashboard.class));
    }


    @Test
    void stockDashboardService_deleteStockDashboard_() {
        StockDashboard dashboard = StockDashboard.builder().id(DASHBOARD_ID).build();

        when(stockDashboardRepository.findById(DASHBOARD_ID)).thenReturn(Optional.of(dashboard));

        stockDashboardService.deleteStockDashboard(DASHBOARD_ID);

        verify(stockDashboardRepository, times(1)).findById(DASHBOARD_ID);
        verify(stockDashboardRepository, times(1)).deleteById(DASHBOARD_ID);
    }

    @Test
    void stockDashboardService_DeleteStockDashboard_NotFoundStockDasboard_ThrowsException() {
        when(stockDashboardRepository.findById(DASHBOARD_ID)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            stockDashboardService.deleteStockDashboard(DASHBOARD_ID);
        });

        Assertions.assertThat("StockDashboard not found with id: " + DASHBOARD_ID).isEqualTo(exception.getMessage());

        verify(stockDashboardRepository, times(1)).findById(DASHBOARD_ID);
        verify(stockDashboardRepository, times(0)).deleteById(DASHBOARD_ID);
    }

    @Test
    void stockDashboardService_GetAllDashboards_ReturnsListOfDashboards() {
        List<StockDashboard> dashboards = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            StockDashboard dashboard = new StockDashboard();
            dashboard.setId((long) i);
            dashboards.add(dashboard);
        }

        when(stockDashboardRepository.findAll()).thenReturn(dashboards);

        List<StockDashboard> result = stockDashboardService.getAllDashboards();

        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(dashboards.size()).isEqualTo(result.size());
        Assertions.assertThat(dashboards).isEqualTo(result);

        verify(stockDashboardRepository, times(1)).findAll();
    }






}