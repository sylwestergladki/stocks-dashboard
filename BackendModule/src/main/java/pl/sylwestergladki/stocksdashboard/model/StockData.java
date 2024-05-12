package pl.sylwestergladki.stocksdashboard.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;


@Data
public class StockData {
    @JsonProperty(value="Meta Data")
    private StockMetaData stockMetaData;
    @JsonProperty(value = "Time Series (Daily)")
    private Map<String, StockPriceInfo> stockPriceInfo;
}

