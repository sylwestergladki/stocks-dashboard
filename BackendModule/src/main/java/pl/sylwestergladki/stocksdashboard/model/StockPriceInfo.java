package pl.sylwestergladki.stocksdashboard.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockPriceInfo {
    private double open;
    private double high;
    private double low;
    private double close;
    private double volume;


}
