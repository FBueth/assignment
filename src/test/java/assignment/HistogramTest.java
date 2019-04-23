package assignment;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HistogramTest {

    private Histogram histogram = new Histogram();


    @Test
    void regularTimestampAddsToHourlyRequests() {
        //given
        String timestamp = "05:30:45,123";

        //when
        histogram.addEntry(timestamp);
        ArrayList<Integer> hourlyRequests = histogram.getHourlyRequests();


        //then
        assertEquals(1, hourlyRequests.get(5));
    }

    @Test
    void numberGreaterThan23ThrowsException() {
        //given
        String timestamp = "28:30:45,123";

        //when
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> histogram.addEntry(timestamp));
        String exceptionMessage = thrown.getMessage();

        //then
        assertTrue(exceptionMessage.contains("Timestamp invalid"));
    }

    @Test
    void stringWithoutNumberThrowsException() {
        //given
        String timestamp = "abc";

        //when
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> histogram.addEntry(timestamp));
        String exceptionMessage = thrown.getMessage();

        //then
        assertTrue(exceptionMessage.contains("Timestamp invalid"));
    }

    @Test
    void printHistogram() {
    }

}