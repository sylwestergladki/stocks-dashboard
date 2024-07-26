package pl.sylwestergladki.stocksdashboard.helpers;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;


class DateHelperTest {

    @Test
    void DateHelperTest_convertTimeStampToLocalDate() {
        long timestamp = System.currentTimeMillis();
        LocalDate expectedDate = LocalDate.now();

        LocalDate result = DateHelper.convertTimestampToLocalDate(timestamp);

        Assertions.assertThat(expectedDate).isEqualTo(result);
    }

}