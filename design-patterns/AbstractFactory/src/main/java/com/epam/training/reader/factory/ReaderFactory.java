package com.epam.training.reader.factory;

import com.epam.training.common.AbstractFactory;
import com.epam.training.reader.impl.DBReader;
import com.epam.training.reader.impl.FileReader;
import com.epam.training.reader.model.Reader;
import com.epam.training.writer.model.Writer;

public class ReaderFactory extends AbstractFactory {

    @Override
    public Reader getReader(STORAGE_TYPE type) throws Exception {
        if (type == STORAGE_TYPE.DB) {
            return new DBReader();
        }

        if (type == STORAGE_TYPE.FILE) {
            return new FileReader();
        }

        return null;
    }

    @Override
    public Writer getWriter(STORAGE_TYPE type) {
        return null;
    }

}
