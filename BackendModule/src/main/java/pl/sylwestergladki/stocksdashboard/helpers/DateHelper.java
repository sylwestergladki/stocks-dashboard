package pl.sylwestergladki.stocksdashboard.helpers;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class DateHelper {
    public static LocalDate convertTimestampToLocalDate(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        return instant.atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
