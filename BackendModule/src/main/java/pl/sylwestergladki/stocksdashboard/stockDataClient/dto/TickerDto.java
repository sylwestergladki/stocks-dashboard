package pl.sylwestergladki.stocksdashboard.stockDataClient.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class TickerDto {
    private String ticker;
    private String name;
    private String market;
    private String locale;

    @JsonProperty("primary_exchange")
    private String primaryExchange;

    private String type;
    private boolean active;

    @JsonProperty("currency_name")
    private String currencyName;

    private String cik;

    @JsonProperty("composite_figi")
    private String compositeFigi;

    @JsonProperty("share_class_figi")
    private String shareClassFigi;

    @JsonProperty("last_updated_utc")
    private LocalDateTime lastUpdatedUtc;
}
