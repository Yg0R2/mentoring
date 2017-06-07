package com.epam.training.writer.factory;

import com.epam.training.common.AbstractFactory;
import com.epam.training.reader.model.Reader;
import com.epam.training.writer.impl.DBWriter;
import com.epam.training.writer.impl.FileWriter;
import com.epam.training.writer.model.Writer;

public class WriterFactory extends AbstractFactory {

    @Override
    public Reader getReader(STORAGE_TYPE type) throws Exception {
        return null;
    }

    @Override
    public Writer getWriter(STORAGE_TYPE type) throws Exception {
        if (type == STORAGE_TYPE.DB) {
            return new DBWriter();
        }

        if (type == STORAGE_TYPE.FILE) {
            return new FileWriter();
        }

        return null;
    }

}
