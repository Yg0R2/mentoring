package com.epam.training;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class DirectoryEntry implements FSEntity {

    private static final Logger LOGGER = LoggerFactory.getLogger(DirectoryEntry.class);

    private List<FSEntity> children = new ArrayList<>();
    private String dirName;

    public DirectoryEntry(String dirName) {
        this.dirName = dirName;
    }

    public void addChild(FSEntity fsEntry) {
        children.add(fsEntry);
    }

    @Override
    public String getName() {
        return dirName;
    }

    @Override
    public long getSize() {
        return children.stream().map(c -> c.getSize()).mapToLong(Long::longValue).sum();
    }

    public void listFiles() {
        children.stream().map(c -> c.getName()).forEach(LOGGER::info);
    }

    public void listTree(int countOfParents) {
        String visualizer = Strings.repeat("  ", countOfParents) + "|-" ;

        for (FSEntity entry : children) {
            LOGGER.info(visualizer + "Name: " + entry.getName() + ", Size: " + entry.getSize());

            if (entry instanceof DirectoryEntry) {
                ((DirectoryEntry) entry).listTree(countOfParents + 1);
            }
        }
    }

    public void removeChild(FSEntity fsEntry) {
        children.remove(fsEntry);
    }

}
