package com.epam.hcomtest.resource;

import com.epam.hcomtest.datastore.model.DataStoreReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

@Component
public class ResourceUIModel {

    private DataStoreReader dataStoreReader;
    private Map<ResourceColor, String> resourceColorDataStoreMap;

    @Autowired
    public ResourceUIModel(DataStoreReader dataStoreReader, Map<ResourceColor, String> resourceColorDataStoreMap) {
        this.dataStoreReader = dataStoreReader;
        this.resourceColorDataStoreMap = resourceColorDataStoreMap;
    }

    public Set<Resource> getResources() {
        Set<Resource> resources = new TreeSet<>();

        resourceColorDataStoreMap.forEach((resourceColor, resourceName) -> {
            List<Resource> dataStoreResources = dataStoreReader.readResources(resourceName);

            dataStoreResources.stream()
                .map(resource -> new Resource(resource.getKey(), resource.getValue(), resourceColor))
                .forEach(resources::add);
        });

        return resources;
    }

}
