package com.epam.mentoring.sequence;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import static org.testng.Assert.assertEquals;

public class ResultTest {

    @Test
    public void testCompareWithSet() {
        // GIVEN

        Result r1 = new Result(0, 3);
        Result r2 = new Result(0, 4);

        // WHEN

        Set<Result> results = new TreeSet<>(Arrays.asList(r2, r1));

        // THEN

        Iterator<Result> resultIterator = results.iterator();

        assertEquals(resultIterator.next(), r1);
        assertEquals(resultIterator.next(), r2);
    }

    @Test
    public void testCompareWithMaxSelection() {
        // GIVEN

        Result r1 = new Result(0, 3);
        Result r2 = new Result(0, 4);

        // WHEN

        Result longestResult = Arrays.asList(r1, r2).stream().max(Result::compareTo).get();

        // THEN

        assertEquals(longestResult, r2);
    }

}
