package org.apache.commons.io.input;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.io.Reader;
import java.util.function.Supplier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BrokenReaderLLM_Test {

    private IOException exception;
    private Reader brokenReader;
    private Supplier<IOException> exceptionSupplier;

    @BeforeEach
    public void setUp() {
        exception = new IOException("test exception");
        exceptionSupplier = () -> new IOException("supplier exception");
        brokenReader = new BrokenReader(exceptionSupplier);
    }

    @Test
    public void testCloseWithSupplier() {
        assertEquals("supplier exception", assertThrows(IOException.class, () -> brokenReader.close()).getMessage());
    }

    @Test
    public void testMarkWithSupplier() {
        assertEquals("supplier exception", assertThrows(IOException.class, () -> brokenReader.mark(1)).getMessage());
    }

    @Test
    public void testReadWithSupplier() {
        assertEquals("supplier exception", assertThrows(IOException.class, () -> brokenReader.read()).getMessage());
    }

    @Test
    public void testReadCharArrayWithSupplier() {
        assertEquals("supplier exception", assertThrows(IOException.class, () -> brokenReader.read(new char[1])).getMessage());
    }

    @Test
    public void testReadCharArrayIndexedWithSupplier() {
        assertEquals("supplier exception", assertThrows(IOException.class, () -> brokenReader.read(new char[1], 0, 1)).getMessage());
    }

    @Test
    public void testReadyWithSupplier() {
        assertEquals("supplier exception", assertThrows(IOException.class, () -> brokenReader.ready()).getMessage());
    }

    @Test
    public void testResetWithSupplier() {
        assertEquals("supplier exception", assertThrows(IOException.class, () -> brokenReader.reset()).getMessage());
    }

    @Test
    public void testSkipWithSupplier() {
        assertEquals("supplier exception", assertThrows(IOException.class, () -> brokenReader.skip(1)).getMessage());
    }

    @Test
    public void testConstructorWithSupplier() {
        BrokenReader reader = new BrokenReader(exceptionSupplier);
        assertNotNull(reader);
    }
}