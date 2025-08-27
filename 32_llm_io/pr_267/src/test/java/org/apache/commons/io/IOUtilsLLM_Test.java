package org.apache.commons.io;

import org.junit.jupiter.api.Test;
import java.io.Closeable;
import java.io.IOException;
import java.util.function.Consumer;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IOUtilsLLM_Test {

    @Test
    public void testCloseQuietlyWithNullConsumer() {
        Closeable closeable = mock(Closeable.class);
        IOUtils.closeQuietly(closeable, null);
        try {
            verify(closeable).close();
        } catch (IOException e) {
            fail("IOException should not be thrown");
        }
    }

    @Test
    public void testCloseQuietlyWithNonNullConsumer() {
        Closeable closeable = mock(Closeable.class);
        Consumer<IOException> consumer = mock(Consumer.class);
        IOException ioException = new IOException("Test Exception");

        doThrow(ioException).when(closeable).close();
        IOUtils.closeQuietly(closeable, consumer);

        verify(consumer).accept(ioException);
    }

    @Test
    public void testCloseQuietlyWithNullCloseable() {
        IOUtils.closeQuietly((Closeable) null, null);
        // No exception should be thrown
    }
}