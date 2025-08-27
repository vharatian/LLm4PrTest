package org.apache.commons.imaging.common;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteOrder;
import static org.junit.jupiter.api.Assertions.*;

public class BinaryFunctionsLLM_Test {

    @Test
    public void testRead8BytesBigEndian() throws IOException {
        byte[] data = new byte[] {
            (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04,
            (byte) 0x05, (byte) 0x06, (byte) 0x07, (byte) 0x08
        };
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        long expected = 0x0102030405060708L;
        long result = BinaryFunctions.read8Bytes("test", inputStream, "Exception message", ByteOrder.BIG_ENDIAN);
        assertEquals(expected, result);
    }

    @Test
    public void testRead8BytesLittleEndian() throws IOException {
        byte[] data = new byte[] {
            (byte) 0x08, (byte) 0x07, (byte) 0x06, (byte) 0x05,
            (byte) 0x04, (byte) 0x03, (byte) 0x02, (byte) 0x01
        };
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        long expected = 0x0102030405060708L;
        long result = BinaryFunctions.read8Bytes("test", inputStream, "Exception message", ByteOrder.LITTLE_ENDIAN);
        assertEquals(expected, result);
    }

    @Test
    public void testRead8BytesIOException() {
        byte[] data = new byte[] {
            (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04,
            (byte) 0x05, (byte) 0x06, (byte) 0x07
        };
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        assertThrows(IOException.class, () -> {
            BinaryFunctions.read8Bytes("test", inputStream, "Exception message", ByteOrder.BIG_ENDIAN);
        });
    }
}