package org.apache.commons.compress.archivers.zip;

import static org.junit.Assert.*;
import java.math.BigInteger;
import org.junit.Test;

public class ZipEightByteIntegerLLM_Test {
    @Test
    public void testEqualsWithNull() {
        final ZipEightByteInteger zl = new ZipEightByteInteger(0x12345678);
        assertFalse("null handling", zl.equals(null));
    }

    @Test
    public void testEqualsWithDifferentClass() {
        final ZipEightByteInteger zl = new ZipEightByteInteger(0x12345678);
        assertFalse("non ZipEightByteInteger handling", zl.equals(new Integer(0x1234)));
    }
}