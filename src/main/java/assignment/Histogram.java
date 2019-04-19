package assignment;

import java.util.ArrayList;
import java.util.Collections;

class Histogram {

    private ArrayList<Integer> hourlyRequests = new ArrayList<>();

    Histogram() {
        addHours();
    }

    private void addHours() {
        for (int i = 0; i < 24; i++) {
            hourlyRequests.add(i, 0);
        }
    }

    void addEntry(String timestamp) {
        try {
            String[] timestampParts = timestamp.split(":");
            String hourAsString = timestampParts[0];
            int hour = Integer.parseInt(hourAsString);
            hourlyRequests.set(hour, hourlyRequests.get(hour) + 1);
        } catch (IndexOutOfBoundsException e){
            throw new IndexOutOfBoundsException("Timestamp invalid:");
        }
    }


    void printHistogram() {
        int divisor = limitHistogramLength();
        System.out.println("Number of hourly requests. Each | represents up to " + divisor + " requests.");

        for (int i = 0; i < 24; i++) {
            String hour = String.format("%02d", i);
            System.out.print(hour + ": ");
            int histogramValue = hourlyRequests.get(i) / divisor;
            for (int j = 0; j < histogramValue; j++) {
                System.out.print("|");
            }
            System.out.print("\n");
        }
    }

    private int limitHistogramLength() {
        int maximumValue = Collections.max(hourlyRequests);
        int divisor = 10;
        while (maximumValue / divisor > 100) {
            divisor *= 10;
        }
        return divisor;
    }


}
