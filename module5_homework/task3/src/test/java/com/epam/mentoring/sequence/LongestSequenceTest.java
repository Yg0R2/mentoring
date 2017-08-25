package com.epam.mentoring.sequence;

import org.powermock.core.classloader.annotations.PrepareEverythingForTest;
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;
import org.powermock.reflect.Whitebox;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@PrepareEverythingForTest
@SuppressStaticInitializationFor("LongestSequence")
public class LongestSequenceTest {

    private LongestSequence underTest;

    @Test
    public void testEmptyArray() {
        // GIVEN

        int[] array = new int[0];

        // WHEN

        underTest = new LongestSequence(array, 17);
        Result result = underTest.compute();

        // THEN

        assertEquals(result.getFromIndex(), 0);
        assertEquals(result.getToIndex(), 0);
        assertEquals(result.getLength(), 0);
    }

    @Test
    public void testNotSearchedNumbersInArray() {
        // GIVEN

        int[] array = new int[] {0, 1, 2, 3, 4, 5, 6};

        // WHEN

        underTest = new LongestSequence(array, 17);
        Result result = underTest.compute();

        // THEN

        assertEquals(result.getFromIndex(), 0);
        assertEquals(result.getToIndex(), 0);
        assertEquals(result.getLength(), 0);
    }

    @Test
    public void testSameNumbersInArray() {
        // GIVEN

        int[] array = new int[] {17, 17, 17, 17, 17};

        // WHEN

        underTest = new LongestSequence(array, 17);
        Result result = underTest.compute();

        // THEN

        assertEquals(result.getFromIndex(), 0);
        assertEquals(result.getToIndex(), array.length -1);
        assertEquals(result.getLength(), 5);
    }

    @Test
    public void testRandomNumbersInArray() {
        // GIVEN

        int[] array = new int[] {17, 17, 16, 16, 16, 16, 17, 17, 17};

        // WHEN

        underTest = new LongestSequence(array, 17);
        Result result = underTest.compute();

        // THEN

        assertEquals(result.getFromIndex(), 6);
        assertEquals(result.getToIndex(), 8);
        assertEquals(result.getLength(), 3);
    }

    @Test
    public void testWhenThresholdIsSmallWithRandomNumbersInArray() {
        // GIVEN

        Whitebox.setInternalState(LongestSequence.class, "THRESHOLD", 5);

        int[] array = new int[] {
            17, 16, 16, 16, 17, 16, 16, 17, 17, 17, 17, 17, 17, 17, 16, 17, 16, 17, 16, 17, 17, 17, 17, 16, 17, 17, 16,
            16, 17, 16, 16, 17, 17, 17, 17, 16, 16, 16, 16, 17, 16, 17, 16, 16, 17, 17, 16, 16, 16, 16, 17, 16, 17, 16,
            16, 16, 17, 17, 16, 16, 17, 16, 17, 17, 17, 17, 17, 16, 17, 16, 16, 16, 16, 17, 17, 16, 16, 17, 16, 16, 16,
            16, 17, 16, 17, 16, 16, 16, 16, 16, 17, 17, 17, 17, 16, 16, 17, 16, 16, 17
        };

        // WHEN

        underTest = new LongestSequence(array, 17);
        Result result = underTest.compute();

        // THEN

        assertEquals(result.getLength(), 7);
    }

}
