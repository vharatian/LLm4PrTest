package org.apache.commons.codec.digest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.charset.StandardCharsets;

import org.junit.Test;

public class UnixCryptLLM_Test {

    @Test
    public void testByteToUnsigned() {
        // Test positive byte
        byte positiveByte = 100;
        assertEquals(100, UnixCrypt.byteToUnsigned(positiveByte));

        // Test negative byte
        byte negativeByte = -100;
        assertEquals(156, UnixCrypt.byteToUnsigned(negativeByte));

        // Test zero byte
        byte zeroByte = 0;
        assertEquals(0, UnixCrypt.byteToUnsigned(zeroByte));

        // Test maximum byte value
        byte maxByte = 127;
        assertEquals(127, UnixCrypt.byteToUnsigned(maxByte));

        // Test minimum byte value
        byte minByte = -128;
        assertEquals(128, UnixCrypt.byteToUnsigned(minByte));
    }
}