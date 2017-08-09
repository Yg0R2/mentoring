package com.epam.hcomtest.datastore.impl;

import com.epam.hcomtest.datastore.exception.DataStoreNotFoundException;
import com.epam.hcomtest.datastore.exception.InvalidDataStoreFormatException;
import com.epam.hcomtest.datastore.model.DataStoreReader;
import com.epam.hcomtest.resource.Resource;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class FileDataStoreReaderTest {

    private DataStoreReader underTest;

    @BeforeMethod
    public void beforeMethod() {
        underTest = new FileDataStoreReader();
    }

    @Test(expectedExceptions = DataStoreNotFoundException.class)
    public void testReadNonExistingResource() {
        // GIVEN

        String invalidResourceName = "invalid.csv";

        // WHEN

        underTest.readResources(invalidResourceName);

        // THEN
    }

    @Test(expectedExceptions = InvalidDataStoreFormatException.class)
    public void testReadInvalidResourceLine() {
        // GIVEN

        String validResourceName = "InvalidLineDataStore.csv";

        // WHEN

        underTest.readResources(validResourceName);

        // THEN
    }

    @Test
    public void testReadResouces() {
        // GIVEN

        String validResourceName = "ValidDataStore.csv";

        // WHEN

        List<Resource> resources = underTest.readResources(validResourceName);

        // THEN

        assertEquals(resources.size(), 5);
    }

}
