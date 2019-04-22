package assignment;

import java.io.*;
import java.util.ArrayList;

class LogFile {

    private String fileName;
    private int limitForResources;
    private ResourceList resourceList = new ResourceList();
    private Histogram histogram = new Histogram();
    private ArrayList<String> faultyEntries = new ArrayList<>();

    LogFile(String[] arguments) {
        this.fileName = arguments[0];
        this.limitForResources = Integer.parseInt(arguments[1]);
    }


    void processFile() {
        String thisLine;

        try (BufferedReader br = new BufferedReader(new FileReader(this.fileName))) {
            while ((thisLine = br.readLine()) != null) {
                if (!thisLine.isEmpty()) {
                    parseEntry(thisLine);
                }
            }
            resourceList.printResources(limitForResources);
            histogram.printHistogram();
            recordFaultyEntries();
        } catch (IOException e) {
            System.out.println("Error processing the file. Please try again.");
        }
    }

    private void parseEntry(String entry) {
        try {
            String[] parts = entry.split(" ");

            String timestamp = parts[1];
            histogram.addEntry(timestamp);

            String resourceName = identifyResource(parts);
            int requestDuration = isValidNumber(parts[parts.length - 1]);
            resourceList.update(resourceName, requestDuration);
        } catch (IllegalArgumentException e) {
            String exceptionMessage = e.getMessage() + "\n";
            String faultyEntry = exceptionMessage.concat(entry + "\n\n");
            faultyEntries.add(faultyEntry);
        }
    }

    private String identifyResource(String[] entry) {
        try {
            String resourceComplete = entry[4];
            if (entry.length == 7 && hasQueryString(resourceComplete)) {
                return parseQuery(resourceComplete);
            } else {
                return resourceComplete;
            }
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Resource could not be identified. Expected the entry to have at least 5 parts, but found only " + entry.length, e);
        }
    }

    private int isValidNumber(String numberAsString) {
        try {
            return Integer.parseInt(numberAsString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("No valid request duration found. Expected a number but found '" + numberAsString + "'", e);
        }
    }

    private boolean hasQueryString(String resourceString) {
        return (resourceString.contains("?") && !resourceString.endsWith("?"));
    }

    private String parseQuery(String resourceString) {
        String[] resourceSplit = resourceString.split("\\?");
        String uri = resourceSplit[0];
        String query = resourceSplit[1];
        if (query.contains("contentId")) {
            return appendContentId(uri, query);
        } else {
            return uri;
        }
    }

    private String appendContentId(String uriString, String queryString) {
        int indexContentIdStart = queryString.indexOf("contentId=");
        String contentId = queryString.substring(indexContentIdStart);
        return uriString + " with " + contentId;
    }

    private void recordFaultyEntries() {
        if (!faultyEntries.isEmpty()) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("faultyLogEntries.txt"))) {
                for (String str : faultyEntries) {
                    bw.write(str);
                }
                System.out.println("\n" + faultyEntries.size() + " log entries could not be processed. They are saved with description in faultyLogEntries.txt.");
            } catch (IOException e) {
                System.out.println("A file for the faulty log entries could not be created.");
            }
        }
    }
}
