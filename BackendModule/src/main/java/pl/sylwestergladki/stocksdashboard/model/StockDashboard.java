package pl.sylwestergladki.stocksdashboard.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.stereotype.Component;


@Data
@Entity
@Component
public class StockDashboard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String symbol;

    public StockDashboard(){

    }

    public StockDashboard(String symbol){
        this.symbol = symbol;
    }

}
