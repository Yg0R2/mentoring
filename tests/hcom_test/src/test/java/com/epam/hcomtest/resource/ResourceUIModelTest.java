package com.epam.hcomtest.resource;

import com.epam.hcomtest.datastore.model.DataStoreReader;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ResourceUIModelTest {

    private ResourceUIModel underTest;
    @Mock
    private DataStoreReader mockDataStoreReader;
    private Map<ResourceColor, String> mockResourceColorDataStoreMap;
    private List<Resource> mockResources;

    private ResourceTestBuilder resourceTestBuilder = new ResourceTestBuilder();

    private static final String VALID_BLUE_RESOURCE_NAME = "validBlueResourceName";
    private static final String VALID_GREEN_RESOURCE_NAME = "validGreenResourceName";
    private static final String VALID_RED_RESOURCE_NAME = "validRedResourceName";

    @BeforeMethod
    public void beforeMethod() {
        MockitoAnnotations.initMocks(this);

        mockResourceColorDataStoreMap = new HashMap<>();
        mockResourceColorDataStoreMap.put(ResourceColor.BLUE, VALID_BLUE_RESOURCE_NAME);
        mockResourceColorDataStoreMap.put(ResourceColor.GREEN, VALID_GREEN_RESOURCE_NAME);
        mockResourceColorDataStoreMap.put(ResourceColor.RED, VALID_RED_RESOURCE_NAME);

        underTest = new ResourceUIModel(mockDataStoreReader, mockResourceColorDataStoreMap);
    }

    @Test
    public void testWithOneDataStoreEntry() {
        // GIVEN

        mockResources = Arrays.asList(resourceTestBuilder.withColor(ResourceColor.RED).build());

        doReturn(mockResources).when(mockDataStoreReader).readResources(VALID_RED_RESOURCE_NAME);


        // WHEN

        Set<Resource> resourceSet = underTest.getResources();

        // THEN

        assertEquals(resourceSet.size(), 1);
        assertResourceLists(new ArrayList<>(resourceSet), mockResources);
    }

    @Test
    public void testWithMultipleDataStoreEntries() {
        // GIVEN

        Resource blueResource = resourceTestBuilder.withColor(ResourceColor.BLUE).withKey(0).build();
        Resource greenResource = resourceTestBuilder.withColor(ResourceColor.GREEN).withKey(1).build();
        Resource redResource = resourceTestBuilder.withColor(ResourceColor.RED).withKey(2).build();

        mockResources = Arrays.asList(blueResource, greenResource, redResource);

        doReturn(Arrays.asList(blueResource)).when(mockDataStoreReader).readResources(VALID_BLUE_RESOURCE_NAME);
        doReturn(Arrays.asList(greenResource)).when(mockDataStoreReader).readResources(VALID_GREEN_RESOURCE_NAME);
        doReturn(Arrays.asList(redResource)).when(mockDataStoreReader).readResources(VALID_RED_RESOURCE_NAME);

        // WHEN

        Set<Resource> resourceSet = underTest.getResources();

        // THEN

        assertEquals(resourceSet.size(), 3);
        assertResourceLists(new ArrayList<>(resourceSet), mockResources);
    }

    public void assertResourceLists(List<Resource> actualList, List<Resource> expectedList) {
        assertEquals(actualList.size(), expectedList.size());

        for (int i = 0; i < expectedList.size(); i++) {
            Resource actual = actualList.get(i);
            Resource expected = expectedList.get(i);

            assertEquals(actual.getColor(), expected.getColor());
            assertEquals(actual.getKey(), expected.getKey());
            assertEquals(actual.getValue(), expected.getValue());
        }
    }
}
