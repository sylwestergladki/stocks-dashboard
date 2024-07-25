package pl.sylwestergladki.stocksdashboard.repository;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.sylwestergladki.stocksdashboard.model.StockDashboard;
import pl.sylwestergladki.stocksdashboard.stockDataClient.dto.ResultDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class StockDashboardRepositoryTest {

    @Autowired
    private StockDashboardRepository stockDashboardRepository;


    @Test
    public void StockDashboardRepository_Save_ReturnSavedStockDashboard(){
        StockDashboard stockDashboard = getStockDashboard();

        StockDashboard savedStockDashboard = stockDashboardRepository.save(stockDashboard);

        Assertions.assertThat(savedStockDashboard).isNotNull();
        Assertions.assertThat(savedStockDashboard.getId()).isGreaterThan(0);
    }

    @Test
    public void StockDashboardRepository_GetAll_ReturnsMoreThanOneStockDashboard(){
        StockDashboard stockDashboard = getStockDashboard();
        StockDashboard stockDashboard2 = getStockDashboard();

        stockDashboardRepository.save(stockDashboard);
        stockDashboardRepository.save(stockDashboard2);

        List<StockDashboard> listOfStockDashboards = stockDashboardRepository.findAll();

        Assertions.assertThat(listOfStockDashboards).isNotNull();
        Assertions.assertThat(listOfStockDashboards.size()).isEqualTo(2);
    }

    @Test
    public void StockDashboardRepository_DeleteStockDashboard_ReturnStockDashboardIsEmpty(){
        StockDashboard stockDashboard = getStockDashboard();

        stockDashboardRepository.save(stockDashboard);

        stockDashboardRepository.deleteById(stockDashboard.getId());

        Optional<StockDashboard> stockDashboardbyId = stockDashboardRepository.findById(stockDashboard.getId());

        Assertions.assertThat(stockDashboardbyId).isEmpty();
    }



    private StockDashboard getStockDashboard() {
        ResultDto resultDto = ResultDto.builder().v(1.0).vw(1.0).o(1.0).c(1.0).h(1.0).l(1.0).t(1L)
                .n(1).build();

        LocalDate localdate = LocalDate.of(2024, 1, 1);

        return StockDashboard.builder().symbol("AAPL")
                .stockPriceInfo(Map.of(localdate,resultDto,
                        localdate.plusDays(1),resultDto,
                        localdate.plusDays(2),resultDto
                )).build();
    }
}