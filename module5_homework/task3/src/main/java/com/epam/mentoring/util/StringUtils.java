package com.epam.mentoring.util;

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringUtils {

    public static final String getArrayAsString(int[] array) {
        return Arrays.stream(array)
            .mapToObj(String::valueOf)
            .collect(Collectors.joining(", "));
    }

    public static final String getArrayAsString(Supplier<IntStream> arraySupplier) {
        return arraySupplier.get()
            .mapToObj(String::valueOf)
            .collect(Collectors.joining(", "));
    }

}
