package org.apache.commons.io.input;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.nio.ByteBuffer;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;

public class ByteBufferCleanerLLM_Test {

    /**
     * Test to ensure that the ByteBufferCleaner can handle a buffer with a specific size.
     */
    @Test
    void testCleanSpecificSize() {
        final ByteBuffer buffer = ByteBuffer.allocateDirect(20);
        buffer.put(RandomUtils.nextBytes(20), 0, 20);
        ByteBufferCleaner.clean(buffer);
    }

    /**
     * Test to ensure that the ByteBufferCleaner throws an IllegalStateException when cleaning fails.
     */
    @Test
    void testCleanFailure() {
        final ByteBuffer buffer = ByteBuffer.allocateDirect(10);
        try {
            ByteBufferCleaner.clean(buffer);
        } catch (IllegalStateException e) {
            assertTrue(e.getMessage().contains("Failed to clean direct buffer."));
        }
    }

    /**
     * Test to ensure that the ByteBufferCleaner handles null buffers gracefully.
     */
    @Test
    void testCleanNullBuffer() {
        try {
            ByteBufferCleaner.clean(null);
        } catch (IllegalStateException e) {
            assertTrue(e.getMessage().contains("Failed to clean direct buffer."));
        }
    }
}