package assignment;

class Resource {
    private String resourceName;
    private int numberOfOccurrences;
    private int requestDurationTotal;

    Resource(String resourceName, int requestDurationTotal) {
        this.resourceName = resourceName;
        this.numberOfOccurrences = 1;
        this.requestDurationTotal = requestDurationTotal;
    }


    int getRequestDurationAverage() {
        return this.requestDurationTotal/this.numberOfOccurrences;
    }

    void incrementRequestDurationTotal(int requestDuration) {
        incrementNumberOfOccurrences();
        this.requestDurationTotal += requestDuration;
    }

    private void incrementNumberOfOccurrences() {
        this.numberOfOccurrences += 1;
    }
}
