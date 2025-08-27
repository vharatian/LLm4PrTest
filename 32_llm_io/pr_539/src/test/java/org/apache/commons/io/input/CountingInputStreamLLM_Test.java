package org.apache.commons.io.input;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class CountingInputStreamLLM_Test {

    @Test
    public void testGetCountThrowsArithmeticException() {
        final long size = (long) Integer.MAX_VALUE + 1;
        final NullInputStream mock = new NullInputStream(size);
        final CountingInputStream cis = new CountingInputStream(mock);
        assertThrows(ArithmeticException.class, () -> cis.getCount());
    }

    @Test
    public void testResetCountThrowsArithmeticException() {
        final long size = (long) Integer.MAX_VALUE + 1;
        final NullInputStream mock = new NullInputStream(size);
        final CountingInputStream cis = new CountingInputStream(mock);
        assertThrows(ArithmeticException.class, () -> cis.resetCount());
    }
}