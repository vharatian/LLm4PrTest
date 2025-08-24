package org.apache.commons.compress.archivers.zip;

import org.junit.Test;
import static org.junit.Assert.*;

public class ZipLongLLM_Test {

    @Test
    public void testEqualsWithNull() {
        final ZipLong zl = new ZipLong(0x12345678);
        assertFalse("null handling", zl.equals(null));
    }

    @Test
    public void testEqualsWithDifferentType() {
        final ZipLong zl = new ZipLong(0x12345678);
        assertFalse("non ZipLong handling", zl.equals(new Integer(0x1234)));
    }
}