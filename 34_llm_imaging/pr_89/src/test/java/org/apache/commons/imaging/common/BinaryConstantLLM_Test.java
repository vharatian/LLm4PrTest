package org.apache.commons.imaging.common;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BinaryConstantLLM_Test {

    @Test
    public void testEqualsWithObject() {
        byte[] value1 = {1, 2, 3};
        byte[] value2 = {1, 2, 3};
        byte[] value3 = {4, 5, 6};

        BinaryConstant bc1 = new BinaryConstant(value1);
        BinaryConstant bc2 = new BinaryConstant(value2);
        BinaryConstant bc3 = new BinaryConstant(value3);

        assertTrue(bc1.equals(bc2));
        assertFalse(bc1.equals(bc3));
        assertFalse(bc1.equals(null));
        assertFalse(bc1.equals(new Object()));
    }

    @Test
    public void testEqualsWithByteArray() {
        byte[] value1 = {1, 2, 3};
        byte[] value2 = {1, 2, 3};
        byte[] value3 = {4, 5, 6};

        BinaryConstant bc = new BinaryConstant(value1);

        assertTrue(bc.equals(value2));
        assertFalse(bc.equals(value3));
    }

    @Test
    public void testEqualsWithByteArrayAndOffset() {
        byte[] value1 = {1, 2, 3};
        byte[] value2 = {0, 1, 2, 3, 0};
        byte[] value3 = {0, 4, 5, 6, 0};

        BinaryConstant bc = new BinaryConstant(value1);

        assertTrue(bc.equals(value2, 1, 3));
        assertFalse(bc.equals(value3, 1, 3));
    }

    @Test
    public void testHashCode() {
        byte[] value1 = {1, 2, 3};
        byte[] value2 = {1, 2, 3};
        byte[] value3 = {4, 5, 6};

        BinaryConstant bc1 = new BinaryConstant(value1);
        BinaryConstant bc2 = new BinaryConstant(value2);
        BinaryConstant bc3 = new BinaryConstant(value3);

        assertEquals(bc1.hashCode(), bc2.hashCode());
        assertNotEquals(bc1.hashCode(), bc3.hashCode());
    }

    @Test
    public void testGet() {
        byte[] value = {1, 2, 3};
        BinaryConstant bc = new BinaryConstant(value);

        assertEquals(1, bc.get(0));
        assertEquals(2, bc.get(1));
        assertEquals(3, bc.get(2));
    }

    @Test
    public void testSize() {
        byte[] value = {1, 2, 3};
        BinaryConstant bc = new BinaryConstant(value);

        assertEquals(3, bc.size());
    }

    @Test
    public void testToByteArray() {
        byte[] value = {1, 2, 3};
        BinaryConstant bc = new BinaryConstant(value);

        assertArrayEquals(value, bc.toByteArray());
    }

    @Test
    public void testWriteTo() throws IOException {
        byte[] value = {1, 2, 3};
        BinaryConstant bc = new BinaryConstant(value);

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        bc.writeTo(os);

        assertArrayEquals(value, os.toByteArray());
    }
}