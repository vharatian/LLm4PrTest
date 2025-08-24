package org.apache.commons.compress.archivers.zip;

import static org.junit.Assert.*;
import org.junit.Test;

public class ZipShortLLM_Test {

    @Test
    public void testEqualsWithNull() {
        final ZipShort zs = new ZipShort(0x1234);
        assertFalse("null handling", zs.equals(null));
    }

    @Test
    public void testEqualsWithNonZipShort() {
        final ZipShort zs = new ZipShort(0x1234);
        assertFalse("non ZipShort handling", zs.equals(new Integer(0x1234)));
    }
}