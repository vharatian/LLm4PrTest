package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class ConversionLLM_Test {

    @Test
    public void testUuidToByteArray() {
        // Test case for nBytes less than 8
        byte[] result = Conversion.uuidToByteArray(new UUID(0x1234567890ABCDEFL, 0xFEDCBA9876543210L), new byte[16], 0, 4);
        assertArrayEquals(new byte[]{(byte) 0xEF, (byte) 0xCD, (byte) 0xAB, (byte) 0x90, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, result);

        // Test case for nBytes exactly 8
        result = Conversion.uuidToByteArray(new UUID(0x1234567890ABCDEFL, 0xFEDCBA9876543210L), new byte[16], 0, 8);
        assertArrayEquals(new byte[]{(byte) 0xEF, (byte) 0xCD, (byte) 0xAB, (byte) 0x90, (byte) 0x78, (byte) 0x56, (byte) 0x34, (byte) 0x12, 0, 0, 0, 0, 0, 0, 0, 0}, result);

        // Test case for nBytes more than 8 but less than 16
        result = Conversion.uuidToByteArray(new UUID(0x1234567890ABCDEFL, 0xFEDCBA9876543210L), new byte[16], 0, 12);
        assertArrayEquals(new byte[]{(byte) 0xEF, (byte) 0xCD, (byte) 0xAB, (byte) 0x90, (byte) 0x78, (byte) 0x56, (byte) 0x34, (byte) 0x12, (byte) 0x10, (byte) 0x32, (byte) 0x54, (byte) 0x76, 0, 0, 0, 0}, result);

        // Test case for nBytes exactly 16
        result = Conversion.uuidToByteArray(new UUID(0x1234567890ABCDEFL, 0xFEDCBA9876543210L), new byte[16], 0, 16);
        assertArrayEquals(new byte[]{(byte) 0xEF, (byte) 0xCD, (byte) 0xAB, (byte) 0x90, (byte) 0x78, (byte) 0x56, (byte) 0x34, (byte) 0x12, (byte) 0x10, (byte) 0x32, (byte) 0x54, (byte) 0x76, (byte) 0x98, (byte) 0xBA, (byte) 0xDC, (byte) 0xFE}, result);

        // Test case for nBytes greater than 16 should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> Conversion.uuidToByteArray(new UUID(0x1234567890ABCDEFL, 0xFEDCBA9876543210L), new byte[16], 0, 17));
    }
}