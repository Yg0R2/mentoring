package com.epam.mentoring.web.converter;

import com.epam.mentoring.api.controller.InventoryRestController;
import com.epam.mentoring.request.InventoryRequest;
import com.epam.mentoring.response.InventoryResponse;
import com.epam.mentoring.mapper.InventoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class InventoryConverter implements Converter<String, InventoryRequest> {

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryConverter.class);

    @Autowired
    private InventoryMapper inventoryMapper;
    @Autowired
    private InventoryRestController inventoryRestController;

    @Override
    public InventoryRequest convert(String source) {
        try {
            long longValue = Long.valueOf(source);

            InventoryResponse inventoryResponse = inventoryRestController.getInventory(longValue);

            return inventoryMapper.mapToRequest(inventoryResponse);
        }
        catch (Exception e) {
            LOGGER.error("Unable to convert the given source: '{}' to inventory Id.", source);
            LOGGER.error(e.getMessage(), e);

            return null;
        }
    }

}
