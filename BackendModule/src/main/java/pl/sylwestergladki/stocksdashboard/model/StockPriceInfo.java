package pl.sylwestergladki.stocksdashboard.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockPriceInfo {
    @JsonProperty(value = "1. open")
    private double open;
    @JsonProperty(value = "2. high")
    private double high;
    @JsonProperty(value = "3. low")
    private double low;
    @JsonProperty(value = "4. close")
    private double close;
    @JsonProperty(value = "5. volume")
    private double volume;


}
