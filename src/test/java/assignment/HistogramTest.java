package assignment;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HistogramTest {

    private Histogram histogram = new Histogram();


    @Test
    void regular_timestamp_adds_to_hourlyRequests() {
        //given
        String timestamp = "05:30:45,123";

        //when
        histogram.addEntry(timestamp);
        ArrayList<Integer> hourlyRequests = histogram.getHourlyRequests();


        //then
        assertEquals(1, hourlyRequests.get(5));
    }

    @Test
    void number_outside_range_throws_exception() {
        //given
        String timestamp = "28:30:45,123";

        //when
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> histogram.addEntry(timestamp));
        String exceptionMessage = thrown.getMessage();

        //then
        assertTrue(exceptionMessage.contains("Timestamp invalid"));
    }

    @Test
    void string_without_number_throws_exception() {
        //given
        String timestamp = "abc";

        //when
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> histogram.addEntry(timestamp));
        String exceptionMessage = thrown.getMessage();

        //then
        assertTrue(exceptionMessage.contains("Timestamp invalid"));
    }

}