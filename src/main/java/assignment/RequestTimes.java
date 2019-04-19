package assignment;

class RequestTimes {
    private int numberOfOccurrences;
    private int requestDurationTotal;

    RequestTimes(int requestDurationTotal) {
        this.numberOfOccurrences = 1;
        this.requestDurationTotal = requestDurationTotal;
    }


    int getRequestDurationAverage() {
        return this.requestDurationTotal / this.numberOfOccurrences;
    }

    void incrementRequestDurationTotal(int requestDuration) {
        incrementNumberOfOccurrences();
        this.requestDurationTotal += requestDuration;
    }

    private void incrementNumberOfOccurrences() {
        this.numberOfOccurrences += 1;
    }
}
