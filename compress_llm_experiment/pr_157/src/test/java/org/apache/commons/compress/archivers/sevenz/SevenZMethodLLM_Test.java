package org.apache.commons.compress.archivers.sevenz;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SevenZMethodLLM_Test {

    @Test
    public void testGetId() {
        SevenZMethod method = SevenZMethod.LZMA;
        byte[] expectedId = new byte[] { (byte)0x03, (byte)0x01, (byte)0x01 };
        byte[] actualId = method.getId();
        assertArrayEquals(expectedId, actualId, "The ID array should match the expected value");

        // Ensure the returned array is a copy
        actualId[0] = (byte)0x00;
        assertArrayEquals(expectedId, method.getId(), "The original ID array should not be modified");
    }

    @Test
    public void testById() {
        byte[] id = new byte[] { (byte)0x03, (byte)0x01, (byte)0x01 };
        SevenZMethod method = SevenZMethod.byId(id);
        assertNotNull(method, "Method should not be null");
        assertEquals(SevenZMethod.LZMA, method, "The method should be LZMA");

        byte[] invalidId = new byte[] { (byte)0x00, (byte)0x00 };
        assertNull(SevenZMethod.byId(invalidId), "Method should be null for an invalid ID");
    }
}