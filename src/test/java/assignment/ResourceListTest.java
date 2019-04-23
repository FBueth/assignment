package assignment;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ResourceListTest {

    ResourceList resourceList = new ResourceList();

    @Test
    void resource_name_and_duration_are_added_to_list() {
        //given
        String resourceName = "testResource";
        int duration = 100;

        //when
        resourceList.update(resourceName, duration);
        HashMap<String, RequestTimes> resources = resourceList.getResources();
        boolean resourceInList = resources.containsKey(resourceName);

        //then
        assertTrue(resourceInList);
    }
}