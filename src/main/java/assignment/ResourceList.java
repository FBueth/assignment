package assignment;

import java.util.Collections;
import java.util.HashMap;
import java.util.TreeMap;

class ResourceList {
    private HashMap<String, Resource> resources = new HashMap<>();

    ResourceList() {
    }

    void update(String resourceName, int requestDuration) {
        if (resources.containsKey(resourceName)) {
            resources.get(resourceName).incrementRequestDurationTotal(requestDuration);
        } else {
            Resource resource = new Resource(resourceName, requestDuration);
            resources.put(resourceName, resource);
        }
    }

    void printResources(int limit) {
        TreeMap<Integer, String> uniqueResources = new TreeMap<>(Collections.reverseOrder());
        System.out.println("These are the top " + limit + " resources with highest average request duration in descending order:");
        resources.forEach((k, v) -> {
            int average = v.getRequestDurationAverage();
            if (uniqueResources.size() < limit) {
                uniqueResources.put(average, k);
            } else if (uniqueResources.size() >= limit && average > uniqueResources.lastKey()) {
                uniqueResources.remove(uniqueResources.lastKey());
                uniqueResources.put(average, k);
            }
        });

        uniqueResources.forEach((k, v) -> System.out.println(v + ", " + k));
        System.out.println();

    }



}
