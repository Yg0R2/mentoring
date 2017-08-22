package com.epam.mentoring.sequence;

import com.epam.mentoring.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class LongestSequence extends RecursiveTask<Integer> {

    public static final Integer THRESHOLD = 10;

    private static Logger LOGGER = LoggerFactory.getLogger(LongestSequence.class);

    private int[] array;
    private int number;
    private int fromIndex;
    private int toIndex;

    public LongestSequence(int[] array, int number) {
        this.array = array;
        this.number = number;

        fromIndex = 0;
        toIndex = array.length - 1;
    }

    private LongestSequence(int array[], int number, int fromIndex, int toIndex) {
        this.array = array;
        this.number = number;
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
    }

    @Override
    protected Integer compute() {
        int length = getLength();
        int index = getNewIndex();

        if ((length <= THRESHOLD) || (fromIndex + index == toIndex)) {
            return computeDirectly();
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("fromIndex: " + fromIndex + ", index: " + index + ", toIndex: " + toIndex);

            Supplier<IntStream> supplier = () -> Arrays.stream(array).skip(fromIndex).limit(toIndex + 1);

            LOGGER.debug(StringUtils.getArrayAsString(supplier));
        }

        LongestSequence left = new LongestSequence(array, number, fromIndex, (fromIndex + index));
        left.fork();

        LongestSequence right = new LongestSequence(array, number, (fromIndex + index), toIndex);

        return Math.max(right.compute(), left.join());
    }

    private int computeDirectly() {
        int storedCount = 0;
        int actualCount = 1;

        for (int i = fromIndex; i < toIndex; i++) {
            if (array[i] != number) {
                continue;
            }

            boolean isNextValueTheSame = (array[i] == array[i + 1]);

            if (isNextValueTheSame) {
                actualCount++;
            }

            if (actualCount > storedCount) {
                storedCount = actualCount;
            }

            if (!isNextValueTheSame) {
                actualCount = 1;
            }
        }

        return storedCount;
    }

    private int getLength() {
        return toIndex - fromIndex;
    }

    private int getNewIndex() {
        int index = getLength() / 2;
        int fromIndex = this.fromIndex + index;

        while ((fromIndex < toIndex) && (array[fromIndex] == array[fromIndex + 1])) {
            index++;

            fromIndex = this.fromIndex + index;
        }

        return index;
    }

}
