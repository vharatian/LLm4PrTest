package org.apache.commons.io.input;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.ThreadUtils;
import org.junit.jupiter.api.Test;

public class TimestampedObserverLLM_Test {

    @Test
    public void testTimestampedObserverFunctionality() throws IOException, InterruptedException {
        final Instant before = Instant.now();
        ThreadUtils.sleep(Duration.ofMillis(20));
        final TimestampedObserver timestampedObserver = new TimestampedObserver();
        assertFalse(timestampedObserver.isClosed());
        ThreadUtils.sleep(Duration.ofMillis(20));
        assertNotNull(timestampedObserver.toString());
        assertTrue(timestampedObserver.getOpenInstant().isAfter(before));
        assertTrue(timestampedObserver.getOpenToNowDuration().toNanos() > 0);
        assertNull(timestampedObserver.getCloseInstant());
        assertFalse(timestampedObserver.isClosed());
        final byte[] buffer = MessageDigestCalculatingInputStreamTest.generateRandomByteStream(IOUtils.DEFAULT_BUFFER_SIZE);
        try (ObservableInputStream ois = new ObservableInputStream(new ByteArrayInputStream(buffer), timestampedObserver)) {
            assertTrue(timestampedObserver.getOpenInstant().isAfter(before));
            assertTrue(timestampedObserver.getOpenToNowDuration().toNanos() > 0);
            assertFalse(timestampedObserver.isClosed());
        }
        assertTrue(timestampedObserver.isClosed());
        assertTrue(timestampedObserver.getOpenInstant().isAfter(before));
        assertTrue(timestampedObserver.getOpenToNowDuration().toNanos() > 0);
        assertTrue(timestampedObserver.getCloseInstant().isAfter(timestampedObserver.getOpenInstant()));
        assertTrue(timestampedObserver.getOpenToCloseDuration().toNanos() > 0);
        assertNotNull(timestampedObserver.toString());
    }

    @Test
    public void testExampleUsage() throws IOException {
        final TimestampedObserver timestampedObserver = new TimestampedObserver();
        final byte[] buffer = MessageDigestCalculatingInputStreamTest
                .generateRandomByteStream(IOUtils.DEFAULT_BUFFER_SIZE);
        try (ObservableInputStream ois = new ObservableInputStream(new ByteArrayInputStream(buffer),
                timestampedObserver)) {
        }
    }
}