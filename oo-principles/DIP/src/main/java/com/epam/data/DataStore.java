package com.epam.data;

public interface DataStore<T> {

    void add(T t);

    Iterable<T> list();

}
