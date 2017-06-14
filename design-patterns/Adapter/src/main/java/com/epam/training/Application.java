package com.epam.training;

import com.epam.training.wrapper.ListWrapper;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        ListWrapper wrapper = new ListWrapper(new ArrayList<>());

        LOGGER.info("--- Populate values ---");

        for (Object o : Lists.newArrayList('1', "2", 3, 4l, 5D, 6f)) {
            wrapper.push(o);
        }

        LOGGER.info("--- Read values ---");

        while (wrapper.pop() != null){
        }

    }

}
