package org.apache.commons.io.input;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystemNotFoundException;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class BrokenInputStreamLLM_Test {

    static final class CustomeException extends Exception {
        private static final long serialVersionUID = 1L;
    }

    static Stream<Class<? extends Throwable>> parameters() {
        return Stream.of(
            IOException.class,
            FileNotFoundException.class,
            FileSystemNotFoundException.class,
            RuntimeException.class,
            IllegalArgumentException.class,
            IllegalStateException.class,
            Error.class,
            ExceptionInInitializerError.class,
            CustomeException.class
        );
    }

    private BrokenInputStream createBrokenInputStream(final Throwable exception) {
        if (exception instanceof IOException) {
            return new BrokenInputStream((IOException) exception);
        }
        return new BrokenInputStream(exception);
    }

    @ParameterizedTest
    @MethodSource("parameters")
    public void testAvailable(final Class<Exception> clazz) throws Exception {
        final Throwable exception = clazz.newInstance();
        @SuppressWarnings("resource")
        final BrokenInputStream stream = createBrokenInputStream(exception);
        assertEquals(exception, assertThrows(exception.getClass(), () -> stream.available()));
    }

    @ParameterizedTest
    @MethodSource("parameters")
    public void testClose(final Class<Exception> clazz) throws Exception {
        final Throwable exception = clazz.newInstance();
        @SuppressWarnings("resource")
        final BrokenInputStream stream = createBrokenInputStream(exception);
        assertEquals(exception, assertThrows(exception.getClass(), () -> stream.close()));
    }

    @ParameterizedTest
    @MethodSource("parameters")
    public void testRead(final Class<Exception> clazz) throws Exception {
        final Throwable exception = clazz.newInstance();
        @SuppressWarnings("resource")
        final BrokenInputStream stream = createBrokenInputStream(exception);
        assertEquals(exception, assertThrows(exception.getClass(), () -> stream.read()));
        assertEquals(exception, assertThrows(exception.getClass(), () -> stream.read(new byte[1])));
        assertEquals(exception, assertThrows(exception.getClass(), () -> stream.read(new byte[1], 0, 1)));
    }

    @ParameterizedTest
    @MethodSource("parameters")
    public void testReset(final Class<Exception> clazz) throws Exception {
        final Throwable exception = clazz.newInstance();
        @SuppressWarnings("resource")
        final BrokenInputStream stream = createBrokenInputStream(exception);
        assertEquals(exception, assertThrows(exception.getClass(), () -> stream.reset()));
    }

    @ParameterizedTest
    @MethodSource("parameters")
    public void testSkip(final Class<Exception> clazz) throws Exception {
        final Throwable exception = clazz.newInstance();
        @SuppressWarnings("resource")
        final BrokenInputStream stream = createBrokenInputStream(exception);
        assertEquals(exception, assertThrows(exception.getClass(), () -> stream.skip(1)));
    }

    @Test
    public void testTryWithResources() throws Exception {
        final IOException thrown = assertThrows(IOException.class, () -> {
            try (InputStream newStream = new BrokenInputStream()) {
                newStream.read();
            }
        });
        assertEquals("Broken input stream", thrown.getMessage());
        final Throwable[] suppressed = thrown.getSuppressed();
        assertEquals(1, suppressed.length);
        assertEquals(IOException.class, suppressed[0].getClass());
        assertEquals("Broken input stream", suppressed[0].getMessage());
    }
}