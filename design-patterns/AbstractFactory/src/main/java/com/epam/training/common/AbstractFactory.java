package com.epam.training.common;

import com.epam.training.reader.model.Reader;
import com.epam.training.writer.model.Writer;

public abstract class AbstractFactory {

    public enum STORAGE_TYPE {
        DB, FILE
    }

    public abstract Reader getReader(STORAGE_TYPE type) throws Exception;

    public abstract Writer getWriter(STORAGE_TYPE type) throws Exception;

}
