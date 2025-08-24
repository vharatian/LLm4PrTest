package org.apache.commons.compress.harmony.pack200;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Pack200ClassReaderLLM_Test {

    @Test
    public void testReadUnsignedShortWithIndexZero() {
        byte[] bytecode = new byte[]{0, 0, 0, 0};
        Pack200ClassReader reader = new Pack200ClassReader(bytecode);
        int result = reader.readUnsignedShort(0);
        assertEquals(0, result);
        assertEquals(Short.MIN_VALUE, reader.lastUnsignedShort);
    }

    @Test
    public void testReadUnsignedShortWithIndexGreaterThanZero() {
        byte[] bytecode = new byte[]{0, 19, 0, 0};
        Pack200ClassReader reader = new Pack200ClassReader(bytecode);
        int result = reader.readUnsignedShort(2);
        assertEquals(0, result);
        assertEquals(0, reader.lastUnsignedShort);
    }

    @Test
    public void testReadUnsignedShortWithIndexGreaterThanZeroAndNot19() {
        byte[] bytecode = new byte[]{0, 18, 0, 0};
        Pack200ClassReader reader = new Pack200ClassReader(bytecode);
        int result = reader.readUnsignedShort(2);
        assertEquals(0, result);
        assertEquals(Short.MIN_VALUE, reader.lastUnsignedShort);
    }
}