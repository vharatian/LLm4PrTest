package org.apache.commons.io.input;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.QueueOutputStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.google.common.base.Stopwatch;

import java.util.stream.Stream;

public class QueueInputStreamLLM_Test {

    public static Stream<Arguments> inputData() {
        return Stream.of(
            Arguments.of(""),
            Arguments.of("1"),
            Arguments.of("12"),
            Arguments.of("1234"),
            Arguments.of("12345678"),
            Arguments.of("A".repeat(4095)),
            Arguments.of("A".repeat(4096)),
            Arguments.of("A".repeat(4097)),
            Arguments.of("A".repeat(8191)),
            Arguments.of("A".repeat(8192)),
            Arguments.of("A".repeat(8193)),
            Arguments.of("A".repeat(8192 * 4))
        );
    }

    private String readUnbuffered(final InputStream inputStream) throws IOException {
        final StringBuilder result = new StringBuilder();
        int n;
        while ((n = inputStream.read()) != -1) {
            result.append((char) n);
        }
        return result.toString();
    }

    @Test
    public void testInvalidArguments() {
        assertThrows(IllegalArgumentException.class, () -> QueueInputStream.builder().setTimeout(Duration.ofNanos(-1)).get(), "timeout must not be negative");
    }

    @Test
    public void testTimeoutConversion() throws IOException {
        try (QueueInputStream queueInputStream = QueueInputStream.builder().setTimeout(Duration.ofNanos(500)).get()) {
            assertEquals(Duration.ofNanos(500), queueInputStream.getTimeout());
        }
    }

    @Test
    @DisplayName("If data is not available in queue, then read will wait until timeout elapses")
    public void testTimeoutUnavailableData() throws IOException {
        try (QueueInputStream inputStream = QueueInputStream.builder().setTimeout(Duration.ofNanos(500_000_000)).get();
             QueueOutputStream outputStream = inputStream.newQueueOutputStream()) {
            final Stopwatch stopwatch = Stopwatch.createStarted();
            final String actualData = assertTimeout(Duration.ofSeconds(1), () -> readUnbuffered(inputStream));
            stopwatch.stop();
            assertEquals("", actualData);
            assertTrue(stopwatch.elapsed(TimeUnit.NANOSECONDS) >= 500_000_000, () -> stopwatch.toString());
        }
    }

    @ParameterizedTest(name = "inputData={0}")
    @MethodSource("inputData")
    public void testUnbufferedReadWriteWithTimeout(final String inputData) throws IOException {
        try (QueueInputStream inputStream = QueueInputStream.builder().setTimeout(Duration.ofNanos(120_000_000_000L)).get();
             QueueOutputStream outputStream = inputStream.newQueueOutputStream()) {
            outputStream.write(inputData.getBytes());
            outputStream.flush();
            final String actualData = assertTimeout(Duration.ofSeconds(1), () -> readUnbuffered(inputStream));
            assertEquals(inputData, actualData);
        }
    }
}