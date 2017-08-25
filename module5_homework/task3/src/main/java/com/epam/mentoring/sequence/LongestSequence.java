package com.epam.mentoring.sequence;

import com.epam.mentoring.util.StringUtils;
import com.google.common.collect.Iterables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.RecursiveTask;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class LongestSequence extends RecursiveTask<Result> {

    public static final Integer THRESHOLD = 10;

    private static Logger LOGGER = LoggerFactory.getLogger(LongestSequence.class);

    private int[] array;
    private int number;
    private int fromIndex;
    private int toIndex;

    public LongestSequence(int[] array, int number) {
        this(array, number, 0, (array.length - 1));
    }

    private LongestSequence(int array[], int number, int fromIndex, int toIndex) {
        this.array = array;
        this.number = number;

        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
    }

    @Override
    protected Result compute() {
        int length = toIndex - fromIndex;

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

        return Arrays.asList(right.compute(), left.join()).stream().max(Result::compareTo).get();
    }

    private Result computeDirectly() {
        int actualCount = 0;

        Set<Result> results = new TreeSet<>();

        for (int index = fromIndex; index < toIndex; index++) {
            if (array[index] != number) {
                continue;
            }

            int nextIndex = index + 1;

            boolean isNextValueTheSame = isNextArrayValueTheSame(array, index);

            if (isNextValueTheSame) {
                actualCount++;
            }

            if (!isNextValueTheSame || (nextIndex == toIndex)) {
                results.add(new Result((nextIndex - actualCount), nextIndex));

                actualCount = 0;
            }
        }

        if (results.isEmpty()) {
            return new Result();
        }

        return Iterables.getLast(results);
    }

    private int getNewIndex() {
        int index = (toIndex - this.fromIndex) / 2;
        int fromIndex = this.fromIndex + index;

        while ((fromIndex < toIndex) && isNextArrayValueTheSame(array, fromIndex)) {
            index++;

            fromIndex = this.fromIndex + index;
        }

        return index;
    }

    private boolean isNextArrayValueTheSame(int[] array, int index) {
        return array[index] == array[index + 1];
    }

}
