package pl.sylwestergladki.stocksdashboard.repository;



import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.sylwestergladki.stocksdashboard.model.StockDashboard;
import pl.sylwestergladki.stocksdashboard.stockDataClient.dto.StockPriceInfo;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class StockDashboardRepositoryTest {

    @Autowired
    private StockDashboardRepository stockDashboardRepository;

    @Test
    public void stockDashboardRepository_Save_ReturnSavedStockDashboard(){
        StockDashboard stockDashboard = getStockDashboard();
        StockDashboard savedStockDashboard = stockDashboardRepository.save(stockDashboard);

        Assertions.assertThat(savedStockDashboard).isNotNull();
        Assertions.assertThat(savedStockDashboard.getId()).isGreaterThan(0);
    }

    @Test
    public void stockDashboardRepository_GetAll_ReturnsMoreThanOneStockDashboard(){
        StockDashboard stockDashboard = getStockDashboard();
        StockDashboard stockDashboard2 = getStockDashboard();

        stockDashboardRepository.save(stockDashboard);
        stockDashboardRepository.save(stockDashboard2);

        List<StockDashboard> stockDashboardList = stockDashboardRepository.findAll();

        Assertions.assertThat(stockDashboardList).isNotNull();
        Assertions.assertThat(stockDashboardList.size()).isEqualTo(2);
    }

    @Test
    public void stockDashboardRepository_FindById_ReturnStockDashboard(){
        StockDashboard stockDashboard = getStockDashboard();

        stockDashboardRepository.save(stockDashboard);

        StockDashboard foundStockDashboard = stockDashboardRepository.findById(stockDashboard.getId()).get();

        Assertions.assertThat(foundStockDashboard).isNotNull();
    }

    @Test
    public void setStockDashboardRepository_StockDashboardDelete_ReturnListIsEmpty(){
        StockDashboard stockDashboard = getStockDashboard();

        stockDashboardRepository.save(stockDashboard);

        stockDashboardRepository.deleteById(stockDashboard.getId());
        Optional<StockDashboard> stockDashboardReturn = stockDashboardRepository.findById(stockDashboard.getId());

        Assertions.assertThat(stockDashboardReturn).isEmpty();
    }


    private static StockDashboard getStockDashboard() {
        StockPriceInfo stockPriceInfo = new StockPriceInfo();
        stockPriceInfo.setOpen(1);
        stockPriceInfo.setHigh(1);
        stockPriceInfo.setLow(1);
        stockPriceInfo.setClose(1);
        Map<String, StockPriceInfo> stringStockDashboardMap = Map.of("2024-01-01", stockPriceInfo);

        StockDashboard stockDashboard = new StockDashboard();
        stockDashboard.setSymbol("IBM");
        stockDashboard.setStockPriceInfo(stringStockDashboardMap);
        return stockDashboard;
    }

}