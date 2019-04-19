package assignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class LogFile {

    private String fileName;
    private int limitForResources;

    private ResourceList resourceList = new ResourceList();
    private Histogram histogram = new Histogram();

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
        } catch (IOException e) {
            System.out.println("Error processing the file. Please try again.");
        }
    }

    private void parseEntry(String entry) {
        String[] parts = entry.split(" ");

        String timestamp = parts[1];
        histogram.addEntry(timestamp);

        String resourceName = identifyResource(parts);
        int requestDuration = Integer.parseInt(parts[parts.length - 1]);
        resourceList.update(resourceName, requestDuration);
    }

    private String identifyResource(String[] entry) {
        String resourceComplete = entry[4];
        if (entry.length == 7 && hasQueryString(resourceComplete)) {
            return parseQuery(resourceComplete);
        } else {
            return resourceComplete;
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
}
