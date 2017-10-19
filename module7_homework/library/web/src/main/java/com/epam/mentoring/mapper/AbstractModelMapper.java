package com.epam.mentoring.mapper;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Type;

public abstract class AbstractModelMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractModelMapper.class);
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    @Autowired
    private Gson gson;

    protected  <S, D> D map(S source, Type mapToType) {
        if (source == null) {
            return null;
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("{}: {}", source.getClass().getTypeName(), gson.toJson(source));
        }

        D destination = MODEL_MAPPER.map(source, mapToType);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("{}: {}", destination.getClass().getTypeName(), gson.toJson(destination));
        }

        return destination;
    }

}
