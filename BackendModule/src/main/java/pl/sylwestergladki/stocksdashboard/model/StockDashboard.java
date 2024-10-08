package pl.sylwestergladki.stocksdashboard.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import pl.sylwestergladki.stocksdashboard.stockDataClient.dto.ResultDto;

import java.time.LocalDate;
import java.util.Map;


@Data
@Builder
@Component
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class StockDashboard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String symbol;
    @ElementCollection
    @CollectionTable(name = "stock_price_info", joinColumns = @JoinColumn(name = "data_id"))
    @MapKeyColumn(name = "date")
    private Map<LocalDate, ResultDto> stockPriceInfo;

}
