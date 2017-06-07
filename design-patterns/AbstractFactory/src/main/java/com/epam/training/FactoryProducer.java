package com.epam.training;

import com.epam.training.common.AbstractFactory;
import com.epam.training.reader.factory.ReaderFactory;
import com.epam.training.writer.factory.WriterFactory;

public class FactoryProducer {

    public enum OPERATION_TYPE {
        READER, WRITER
    }

    public static AbstractFactory getFactory(OPERATION_TYPE type) {
        if (type == OPERATION_TYPE.READER) {
            return new ReaderFactory();
        }

        if (type == OPERATION_TYPE.WRITER) {
            return new WriterFactory();
        }

        return null;
    }

}
