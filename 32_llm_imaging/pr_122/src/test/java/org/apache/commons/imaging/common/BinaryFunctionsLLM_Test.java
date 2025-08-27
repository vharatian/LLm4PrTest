package org.apache.commons.imaging.common;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.junit.jupiter.api.Test;

public class BinaryFunctionsLLM_Test {

    @Test
    public void testReadBytesWithNegativeLength() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(new byte[]{1, 2, 3, 4});
        IOException exception = assertThrows(IOException.class, () -> {
            BinaryFunctions.readBytes("test", inputStream, -1, "Exception message");
        });
        assertEquals("Exception message, invalid length: -1", exception.getMessage());
    }

    @Test
    public void testGetRAFBytesWithNegativeLength() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("testfile", "rw");
        IOException exception = assertThrows(IOException.class, () -> {
            BinaryFunctions.getRAFBytes(raf, 0, -1, "Exception message");
        });
        assertEquals("Exception message, invalid length: -1", exception.getMessage());
        raf.close();
    }
}