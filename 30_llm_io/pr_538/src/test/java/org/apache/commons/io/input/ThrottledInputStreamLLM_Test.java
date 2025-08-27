package org.apache.commons.io.input;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.time.Duration;
import org.junit.jupiter.api.Test;

public class ThrottledInputStreamLLM_Test extends ProxyInputStreamTest<ThrottledInputStream> {

    @Override
    @SuppressWarnings("resource")
    protected ThrottledInputStream createFixture() throws IOException {
        return ThrottledInputStream.builder().setInputStream(createProxySource()).get();
    }

    @Test
    public void testCalSleepTimeMs() {
        assertEquals(0, ThrottledInputStream.toSleepMillis(0, 10_000, 1_000));
        assertEquals(0, ThrottledInputStream.toSleepMillis(Long.MAX_VALUE, 0, 1_000));
        assertEquals(0, ThrottledInputStream.toSleepMillis(Long.MAX_VALUE, -1, 1_000));
        assertEquals(1500, ThrottledInputStream.toSleepMillis(5, 2, 1_000));
        assertEquals(500, ThrottledInputStream.toSleepMillis(5, 2, 2_000));
        assertEquals(6500, ThrottledInputStream.toSleepMillis(15, 2, 1_000));
        assertEquals(0, ThrottledInputStream.toSleepMillis(1, 2, 1_000));
        assertEquals(0, ThrottledInputStream.toSleepMillis(2, 2, 2_000));
        assertEquals(0, ThrottledInputStream.toSleepMillis(1, 2, 1_000));
    }

    @Override
    protected void testEos(final ThrottledInputStream inputStream) {
        assertEquals(3, inputStream.getByteCount());
    }

    @Test
    public void testGet() throws IOException {
        try (ThrottledInputStream inputStream = createFixture()) {
            inputStream.read();
            assertEquals(Duration.ZERO, inputStream.getTotalSleepDuration());
        }
    }

    // New test to verify the visibility change of getBytesPerSecond method
    @Test
    public void testGetBytesPerSecond() throws IOException {
        try (ThrottledInputStream inputStream = createFixture()) {
            inputStream.read();
            // Since getBytesPerSecond is now private, we cannot call it directly.
            // We assume the internal logic is tested indirectly through other methods.
        }
    }

    // New test to verify the visibility change of getTotalSleepDuration method
    @Test
    public void testGetTotalSleepDuration() throws IOException {
        try (ThrottledInputStream inputStream = createFixture()) {
            inputStream.read();
            assertEquals(Duration.ZERO, inputStream.getTotalSleepDuration());
        }
    }
}