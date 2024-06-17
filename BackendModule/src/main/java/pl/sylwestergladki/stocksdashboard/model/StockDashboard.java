package pl.sylwestergladki.stocksdashboard.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import pl.sylwestergladki.stocksdashboard.stockDataClient.dto.StockPriceInfo;

import java.util.Map;


@Data
@Entity
@Component
@NoArgsConstructor
public class StockDashboard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String symbol;
    @ElementCollection
    @CollectionTable(name = "stock_price_info", joinColumns = @JoinColumn(name = "data_id"))
    @MapKeyColumn(name = "date")
    private Map<String, StockPriceInfo> stockPriceInfo;

}
