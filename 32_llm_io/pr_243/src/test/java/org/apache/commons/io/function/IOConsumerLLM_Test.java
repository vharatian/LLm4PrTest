package org.apache.commons.io.function;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import java.io.Closeable;
import java.io.IOException;
import java.io.StringReader;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.test.ThrowOnCloseReader;
import org.junit.jupiter.api.Test;

public class IOConsumerLLM_Test {

    @Test
    public void testNoopIOConsumer() {
        final Closeable nullCloseable = null;
        final IOConsumer<IOException> noopConsumer = IOConsumer.noop();
        assertDoesNotThrow(() -> IOUtils.close(nullCloseable, noopConsumer));
        assertDoesNotThrow(() -> IOUtils.close(new StringReader("s"), noopConsumer));
        assertDoesNotThrow(() -> IOUtils.close(new ThrowOnCloseReader(new StringReader("s")), noopConsumer));
    }

    @Test
    public void testNoopIOConsumerAccessibility() {
        // Ensure that the NOOP_IO_CONSUMER is accessible and functional
        final IOConsumer<IOException> noopConsumer = IOConsumer.noop();
        assertDoesNotThrow(() -> noopConsumer.accept(null));
    }
}