package pl.sylwestergladki.stocksdashboard.stockDataClient.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TickerResponse {
    private List<TickerDto> results;
    private String status;

    @JsonProperty("request_id")
    private String requestId;

    private int count;
}
