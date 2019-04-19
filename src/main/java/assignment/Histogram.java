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
        String[] timestampParts = timestamp.split(":");
        String hour = timestampParts[0];
        if (isValidHour(hour)) {
            int index = Integer.parseInt(hour);
            hourlyRequests.set(index, hourlyRequests.get(index) + 1);
        }
    }

    private boolean isValidHour(String hourAsString) {
        try {
            int hour = Integer.parseInt(hourAsString);
            return (hour >= 0 && hour < 23);
        } catch (NumberFormatException e) {
            return false;
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
