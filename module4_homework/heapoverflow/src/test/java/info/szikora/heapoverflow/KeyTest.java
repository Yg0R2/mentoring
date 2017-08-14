package info.szikora.heapoverflow;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class KeyTest {

    @Test
    public void testKeyWithBigId() {
        // GIVEN

        // WHEN

        Key key1 = new Key(128);
        Key key2 = new Key(128);

        // THEN

        assertEquals((int) key1.getId(), 128);
        assertEquals((int) key2.getId(), 128);

        assertEquals(key1, key2);
    }

    @Test
    public void testKeysWithNullId() {
        // GIVEN

        // WHEN

        Key key1 = new Key(null);
        Key key2 = new Key(null);

        // THEN

        assertEquals(key1.getId(), null);
        assertEquals(key2.getId(), null);

        assertEquals(key1, key2);
    }

    @Test
    public void testKeyWithSmallId() {
        // GIVEN

        // WHEN

        Key key1 = new Key(-129);
        Key key2 = new Key(-129);

        // THEN

        assertEquals((int) key1.getId(), -129);
        assertEquals((int) key2.getId(), -129);
        assertEquals(key1, key2);
    }

}
