package com.epam.training.wrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

public class ListWrapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListWrapper.class);

    private LinkedList<Object> list;

    public ListWrapper(List<Object> list) {
        this.list = new LinkedList<>(list);
    }

    public Object pop() {
        if (list.size() == 0) {
            return null;
        }

        Object lastElement = list.removeLast();

        LOGGER.info("Pop last element: " + toString(lastElement));

        return lastElement;
    }

    public void push(Object o) {
        LOGGER.info("Push element: " + toString(o));

        list.add(o);
    }

    private String toString(Object o) {
        return "[type: " + o.getClass() + ", value: " + String.valueOf(o) + "]";
    }

}
