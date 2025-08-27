package org.apache.commons.io.input;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.io.InputStream;
import java.util.function.Supplier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BrokenInputStreamLLM_Test {

    private Supplier<IOException> exceptionSupplier;
    private InputStream stream;

    @BeforeEach
    public void setUp() {
        exceptionSupplier = () -> new IOException("test exception from supplier");
        stream = new BrokenInputStream(exceptionSupplier);
    }

    @Test
    public void testAvailableWithSupplier() {
        assertEquals(exceptionSupplier.get(), assertThrows(IOException.class, () -> stream.available()));
    }

    @Test
    public void testCloseWithSupplier() {
        assertEquals(exceptionSupplier.get(), assertThrows(IOException.class, () -> stream.close()));
    }

    @Test
    public void testReadWithSupplier() {
        assertEquals(exceptionSupplier.get(), assertThrows(IOException.class, () -> stream.read()));
    }

    @Test
    public void testResetWithSupplier() {
        assertEquals(exceptionSupplier.get(), assertThrows(IOException.class, () -> stream.reset()));
    }

    @Test
    public void testSkipWithSupplier() {
        assertEquals(exceptionSupplier.get(), assertThrows(IOException.class, () -> stream.skip(1)));
    }
}