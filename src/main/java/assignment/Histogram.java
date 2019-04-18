package assignment;

import java.util.TreeMap;

class Histogram {

    private TreeMap<String, Integer> hourlyRequests = new TreeMap<>();

    Histogram() {
        addHours();
    }

    private void addHours() {
        for (int i = 0; i < 24; i ++) {
            String hour = String.format("%02d", i);
            hourlyRequests.put(hour, 0);
        }
    }

    void addEntry(String timestamp) {
        String[] timestampParts = timestamp.split(":");
        String hour = timestampParts[0];
        hourlyRequests.put(hour, hourlyRequests.get(hour) + 1);
    }

    void printHistogram() {
        System.out.println("Number of hourly requests. Each | represents up to 10 requests.");
        hourlyRequests.forEach((k, v) -> {
            System.out.print(k + ": ");
            for(int i = 0; i < v; i += 10) {
                System.out.print("|");
            }
            System.out.print("\n");
        });
        System.out.print("\n");
    }


}
