package com.epam.training;

public class FileEntry implements FSEntity {

    private String fileName;
    private long fileSize;

    public FileEntry(String fileName, long fileSize) {
        this.fileName = fileName;
        this.fileSize = fileSize;
    }

    @Override
    public String getName() {
        return fileName;
    }

    @Override
    public long getSize() {
        return fileSize;
    }
}
