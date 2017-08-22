package com.epam.mentoring;

import com.epam.mentoring.max.FindMax;
import com.epam.mentoring.sequence.LongestSequence;
import com.epam.mentoring.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class Main {

    private static final int SIZE = 100;

    private static Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Main instance = new Main();

        instance.highestNumber();

        instance.longestSequence();
    }

    private void highestNumber() {
        int[] array = new Random().ints(SIZE).toArray();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(StringUtils.getArrayAsString(array));
        }

        FindMax findMax = new FindMax(array, 0, SIZE);
        int maxValue = findMax.invoke();

        LOGGER.info("Highest number in the given array: " + maxValue);
    }

    private void longestSequence() {
        int[] array = new Random().ints(SIZE, 10, 20).toArray();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(StringUtils.getArrayAsString(array));
        }

        LongestSequence longestSequence = new LongestSequence(array, 17);
        int longestSequenceCount = longestSequence.invoke();

        LOGGER.info("Longest sequence of '17': " + longestSequenceCount);
    }

}
