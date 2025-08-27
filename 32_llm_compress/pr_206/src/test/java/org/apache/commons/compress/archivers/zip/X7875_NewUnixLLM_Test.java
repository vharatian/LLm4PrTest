package org.apache.commons.compress.archivers.zip;

import org.apache.commons.compress.utils.ByteUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.Enumeration;
import java.util.zip.ZipException;
import static org.apache.commons.compress.AbstractTestCase.getFile;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class X7875_NewUnixLLM_Test {
    private final static ZipShort X7875 = new ZipShort(0x7875);
    private X7875_NewUnix xf;

    @BeforeEach
    public void before() {
        xf = new X7875_NewUnix();
    }

    /**
     * Test to ensure trimLeadingZeroesForceMinLength returns null when input is null.
     */
    @Test
    public void testTrimLeadingZeroesForceMinLengthNullInput() {
        final byte[] NULL = null;
        assertSame(NULL, X7875_NewUnix.trimLeadingZeroesForceMinLength(NULL));
    }

    /**
     * Test to ensure trimLeadingZeroesForceMinLength handles non-null inputs correctly.
     */
    @Test
    public void testTrimLeadingZeroesForceMinLengthNonNullInput() {
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

    private static byte[] trimTest(final byte[] b) {
        return X7875_NewUnix.trimLeadingZeroesForceMinLength(b);
    }
}