package com.epam.hcomtest.getdata.configuration;

import com.epam.hcomtest.datastore.exception.InvalidDataStoreFormatException;
import com.epam.hcomtest.datastore.impl.FileDataStoreReader;
import com.epam.hcomtest.datastore.model.DataStoreReader;
import com.epam.hcomtest.resource.ResourceColor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class GetDataConfiguration {

    @Bean(name = "dataStoreReader")
    public DataStoreReader dataStoreReader() {
        return new FileDataStoreReader();
    }

    @Bean(name = "resourceColorDataStoreMap")
    public Map<ResourceColor, String> resourceColorDataStoreMap() throws InvalidDataStoreFormatException {
        Map<ResourceColor, String> result = new HashMap<>();

        result.put(ResourceColor.BLUE, "ThrirdPartyDataStore.csv");
        result.put(ResourceColor.GREEN, "SingleSourceOfTruthDataStore.csv");
        result.put(ResourceColor.RED, "OraculumDataStore.csv");

        return result;
    }

}
