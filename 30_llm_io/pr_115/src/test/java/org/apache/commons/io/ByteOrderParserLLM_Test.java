package org.apache.commons.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.nio.ByteOrder;
import org.junit.jupiter.api.Test;

public class ByteOrderParserLLM_Test {

    private ByteOrder parseByteOrder(final String value) {
        return ByteOrderParser.parseByteOrder(value);
    }

    @Test
    public void testParseBig() {
        assertEquals(ByteOrder.BIG_ENDIAN, parseByteOrder("BIG_ENDIAN"));
    }

    @Test
    public void testParseLittle() {
        assertEquals(ByteOrder.LITTLE_ENDIAN, parseByteOrder("LITTLE_ENDIAN"));
    }

    @Test
    public void testThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> parseByteOrder("some value"));
    }

    // New test to ensure the exception message is correct
    @Test
    public void testExceptionMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> parseByteOrder("invalid_value"));
        assertEquals("Unsupported byte order setting: invalid_value, expected one of LITTLE_ENDIAN, BIG_ENDIAN", exception.getMessage());
    }
}