package org.apache.commons.compress.archivers.zip;

import static org.apache.commons.compress.AbstractTestCase.getFile;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.Enumeration;
import java.util.zip.ZipException;

import org.apache.commons.compress.utils.ByteUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class X7875_NewUnixLLM_Test {
    private final static ZipShort X7875 = new ZipShort(0x7875);
    private static byte[] trimTest(final byte[] b) { return X7875_NewUnix.trimLeadingZeroesForceMinLength(b); }
    private X7875_NewUnix xf;

    @BeforeEach
    public void before() {
        xf = new X7875_NewUnix();
    }

    @Test
    public void testTrimLeadingZeroesForceMinLength() {
        final byte[] NULL = null;
        final byte[] EMPTY = ByteUtils.EMPTY_BYTE_ARRAY;
        final byte[] ONE_ZERO = {0};
        final byte[] TWO_ZEROES = {0, 0};
        final byte[] FOUR_ZEROES = {0, 0, 0, 0};
        final byte[] SEQUENCE = {1, 2, 3};
        final byte[] SEQUENCE_LEADING_ZERO = {0, 1, 2, 3};
        final byte[] SEQUENCE_LEADING_ZEROES = {0, 0, 0, 0, 0, 0, 0, 1, 2, 3};
        final byte[] TRAILING_ZERO = {1, 2, 3, 0};
        final byte[] PADDING_ZERO = {0, 1, 2, 3, 0};
        final byte[] SEQUENCE6 = {1, 2, 3, 4, 5, 6};
        final byte[] SEQUENCE6_LEADING_ZERO = {0, 1, 2, 3, 4, 5, 6};

        assertSame(NULL, trimTest(NULL));
        assertArrayEquals(ONE_ZERO, trimTest(EMPTY));
        assertArrayEquals(ONE_ZERO, trimTest(ONE_ZERO));
        assertArrayEquals(ONE_ZERO, trimTest(TWO_ZEROES));
        assertArrayEquals(ONE_ZERO, trimTest(FOUR_ZEROES));
        assertArrayEquals(SEQUENCE, trimTest(SEQUENCE));
        assertArrayEquals(SEQUENCE, trimTest(SEQUENCE_LEADING_ZERO));
        assertArrayEquals(SEQUENCE, trimTest(SEQUENCE_LEADING_ZEROES));
        assertArrayEquals(TRAILING_ZERO, trimTest(TRAILING_ZERO));
        assertArrayEquals(TRAILING_ZERO, trimTest(PADDING_ZERO));
        assertArrayEquals(SEQUENCE6, trimTest(SEQUENCE6));
        assertArrayEquals(SEQUENCE6, trimTest(SEQUENCE6_LEADING_ZERO));
    }

    @Test
    public void testHashCode() {
        X7875_NewUnix xf1 = new X7875_NewUnix();
        X7875_NewUnix xf2 = new X7875_NewUnix();
        assertEquals(xf1.hashCode(), xf2.hashCode());

        xf1.setUID(12345);
        xf1.setGID(54321);
        xf2.setUID(12345);
        xf2.setGID(54321);
        assertEquals(xf1.hashCode(), xf2.hashCode());

        xf2.setUID(54321);
        assertNotEquals(xf1.hashCode(), xf2.hashCode());
    }
}