package com.epam.hcomtest.datastore.model;

import com.epam.hcomtest.datastore.exception.InvalidDataStoreFormatException;
import com.epam.hcomtest.resource.Resource;

import java.util.List;

public interface DataStoreReader {

    List<Resource> readResources(String resourceName) throws InvalidDataStoreFormatException;

}
