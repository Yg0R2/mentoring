package com.epam.mentoring.sequence;

import org.powermock.core.classloader.annotations.PrepareEverythingForTest;
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;
import org.powermock.reflect.Whitebox;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

//@RunWith(PowerMockRunner.class)
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
        int result = underTest.compute();

        // THEN

        assertEquals(result, 0);
    }

    @Test
    public void testNotSearchedNumbersInArray() {
        // GIVEN

        int[] array = new int[] {0, 1, 2, 3, 4, 5, 6};

        // WHEN

        underTest = new LongestSequence(array, 17);
        int result = underTest.compute();

        // THEN

        assertEquals(result, 0);
    }

    @Test
    public void testSameNumbersInArray() {
        // GIVEN

        int[] array = new int[] {17, 17, 17, 17, 17};

        // WHEN

        underTest = new LongestSequence(array, 17);
        int result = underTest.compute();

        // THEN

        assertEquals(result, 5);
    }

    @Test
    public void testRandomNumbersInArray() {
        // GIVEN

        int[] array = new int[] {17, 17, 16, 16, 16, 16, 17, 17, 17};

        // WHEN

        underTest = new LongestSequence(array, 17);
        int result = underTest.compute();

        // THEN

        assertEquals(result, 3);
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
        int result = underTest.compute();

        // THEN

        assertEquals(result, 7);
    }

}
