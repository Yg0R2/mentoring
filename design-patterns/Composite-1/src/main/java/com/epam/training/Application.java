package com.epam.training;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
    private static final Random RND = new Random();

    public static void main(String[] args) {
        Application instance = new Application();

        DirectoryEntry root = new DirectoryEntry("");

        instance.generateDirectories(root);
        instance.generateFiles(root);

        LOGGER.info("--- Tree view of root directory ---");
        LOGGER.info("root");
        root.listTree(0);
        //LOGGER.info("--- Files/Directories in root directory ---");
        //root.listFiles();

        LOGGER.info("Size of the root directory: " + root.getSize());
    }

    private void generateFiles(DirectoryEntry directory) {
        int filesCount = RND.nextInt(5);

        for (int i = 1; i <= filesCount; i++) {
            FSEntity file = new FileEntry("file" + i + ".txt", RND.nextInt(15) + 5);

            directory.addChild(file);
        }
    }

    private void generateDirectories(DirectoryEntry directory) {
        int directoriesCount = RND.nextInt(5);

        for (int i = 1; i <= directoriesCount; i++) {
            DirectoryEntry dir = new DirectoryEntry("directory" + i);

            generateFiles(dir);

            directory.addChild(dir);
        }
    }

}
