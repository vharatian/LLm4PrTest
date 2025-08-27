package org.apache.commons.io.input;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.*;

public class MarkShieldInputStreamLLM_Test {

    @Test
    public void testMarkNotSupported() {
        InputStream inputStream = new ByteArrayInputStream(new byte[]{1, 2, 3});
        MarkShieldInputStream markShieldInputStream = new MarkShieldInputStream(inputStream);
        assertFalse(markShieldInputStream.markSupported(), "markSupported should return false");
    }

    @Test
    public void testMarkDoesNothing() {
        InputStream inputStream = new ByteArrayInputStream(new byte[]{1, 2, 3});
        MarkShieldInputStream markShieldInputStream = new MarkShieldInputStream(inputStream);
        markShieldInputStream.mark(10); // Should do nothing
        assertDoesNotThrow(() -> markShieldInputStream.mark(10), "mark should not throw any exception");
    }

    @Test
    public void testResetThrowsIOException() {
        InputStream inputStream = new ByteArrayInputStream(new byte[]{1, 2, 3});
        MarkShieldInputStream markShieldInputStream = new MarkShieldInputStream(inputStream);
        IOException exception = assertThrows(IOException.class, markShieldInputStream::reset, "reset should throw IOException");
        assertEquals("mark/reset not supported", exception.getMessage(), "Exception message should match");
    }
}