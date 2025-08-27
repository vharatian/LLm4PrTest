package org.apache.commons.io.output;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.io.Writer;
import java.util.function.Supplier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BrokenWriterLLM_Test {

    private IOException exception;
    private Writer brokenWriter;
    private Supplier<IOException> exceptionSupplier;

    @BeforeEach
    public void setUp() {
        exception = new IOException("test exception");
        exceptionSupplier = () -> new IOException("test exception from supplier");
    }

    @Test
    public void testConstructorWithExceptionSupplier() {
        brokenWriter = new BrokenWriter(exceptionSupplier);
        IOException thrown = assertThrows(IOException.class, () -> brokenWriter.write(new char[1]));
        assertEquals("test exception from supplier", thrown.getMessage());
    }

    @Test
    public void testCloseWithExceptionSupplier() {
        brokenWriter = new BrokenWriter(exceptionSupplier);
        IOException thrown = assertThrows(IOException.class, () -> brokenWriter.close());
        assertEquals("test exception from supplier", thrown.getMessage());
    }

    @Test
    public void testFlushWithExceptionSupplier() {
        brokenWriter = new BrokenWriter(exceptionSupplier);
        IOException thrown = assertThrows(IOException.class, () -> brokenWriter.flush());
        assertEquals("test exception from supplier", thrown.getMessage());
    }

    @Test
    public void testWriteWithExceptionSupplier() {
        brokenWriter = new BrokenWriter(exceptionSupplier);
        IOException thrown = assertThrows(IOException.class, () -> brokenWriter.write(new char[1], 0, 1));
        assertEquals("test exception from supplier", thrown.getMessage());
    }
}