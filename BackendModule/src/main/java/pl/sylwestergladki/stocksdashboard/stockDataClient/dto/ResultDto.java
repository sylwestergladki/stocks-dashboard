package pl.sylwestergladki.stocksdashboard.stockDataClient.dto;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Embeddable
public class ResultDto {
    private double v;
    private double vw;
    private double o;
    private double c;
    private double h;
    private double l;
    private long t;
    private int n;
}
