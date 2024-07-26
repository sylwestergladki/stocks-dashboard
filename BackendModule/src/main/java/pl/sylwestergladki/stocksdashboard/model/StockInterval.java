package pl.sylwestergladki.stocksdashboard.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StockInterval {
    DAY("day"),
    MONTH("month"),
    YEAR("year");

    private final String value;
}
