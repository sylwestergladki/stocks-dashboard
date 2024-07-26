package pl.sylwestergladki.stocksdashboard.stockDataClient.dto;


import lombok.Builder;
import lombok.Data;

import java.util.List;


@Builder
@Data
public class StockDto {
    private String ticker;
    private int queryCount;
    private int resultsCount;
    private boolean adjusted;
    private List<ResultDto> results;
    private String status;
    private String requestId;
    private int count;
}

