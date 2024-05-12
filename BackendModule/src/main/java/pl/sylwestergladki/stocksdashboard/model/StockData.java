package pl.sylwestergladki.stocksdashboard.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class StockData {
    @JsonProperty("Meta Data:")
    private StockMetaData stockMetaData;
}

