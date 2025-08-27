package org.apache.commons.io.output;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.io.OutputStream;
import java.util.function.Supplier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BrokenOutputStreamLLM_Test {

    private Supplier<IOException> exceptionSupplier;
    private OutputStream stream;

    @BeforeEach
    public void setUp() {
        exceptionSupplier = () -> new IOException("test exception from supplier");
        stream = new BrokenOutputStream(exceptionSupplier);
    }

    @Test
    public void testCloseWithSupplier() {
        assertEquals(exceptionSupplier.get(), assertThrows(IOException.class, () -> stream.close()));
    }

    @Test
    public void testFlushWithSupplier() {
        assertEquals(exceptionSupplier.get(), assertThrows(IOException.class, () -> stream.flush()));
    }

    @Test
    public void testWriteIntWithSupplier() {
        assertEquals(exceptionSupplier.get(), assertThrows(IOException.class, () -> stream.write(1)));
    }

    @Test
    public void testSingletonInstance() {
        OutputStream singletonStream = BrokenOutputStream.INSTANCE;
        assertEquals(IOException.class, assertThrows(IOException.class, () -> singletonStream.close()).getClass());
    }
}