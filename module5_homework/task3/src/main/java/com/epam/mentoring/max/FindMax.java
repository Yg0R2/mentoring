package com.epam.mentoring.max;

import com.epam.mentoring.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FindMax extends RecursiveTask<Integer> {

    public static final int THRESHOLD = 100;

    private static Logger LOGGER = LoggerFactory.getLogger(FindMax.class);

    private int[] array;
    private int fromIndex;
    private int toIndex;

    public FindMax(int[] array, int fromIndex, int toIndex) {
        this.array = array;
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
    }

    @Override
    protected Integer compute() {
        int length = toIndex - fromIndex;
        if (length < THRESHOLD) {
            return computeDirectly();
        }

        int offset = length / 2;

        FindMax left = new FindMax(array, fromIndex, (fromIndex + offset));
        left.fork();

        FindMax right = new FindMax(array, (fromIndex + offset), toIndex);

        return Math.max(right.compute(), left.join());
    }

    private Integer computeDirectly() {
        Supplier<IntStream> supplier = () -> Arrays.stream(array).skip(fromIndex).limit(toIndex);

        int max = supplier.get().max().getAsInt();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(StringUtils.getArrayAsString(supplier));
            LOGGER.debug("Current max: " + max);
            LOGGER.debug("From index: " + fromIndex + ", to index: " + toIndex);
        }

        return max;
    }

}
