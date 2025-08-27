package org.apache.commons.io.build;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class AbstractStreamBuilderLLM_Test {

    public static class Builder extends AbstractStreamBuilder<char[], Builder> {
        @Override
        public char[] get() {
            final char[] arr = new char[getBufferSize()];
            Arrays.fill(arr, 'a');
            return arr;
        }
    }

    private void assertResult(final char[] arr, final int size) {
        assertNotNull(arr);
        assertEquals(size, arr.length);
        for (final char c : arr) {
            assertEquals('a', c);
        }
    }

    protected Builder builder() {
        return new Builder();
    }

    @Test
    public void testOutputStreamException() {
        final Builder builder = builder();
        assertThrows(UnsupportedOperationException.class, () -> builder.getOutputStream());
    }
}