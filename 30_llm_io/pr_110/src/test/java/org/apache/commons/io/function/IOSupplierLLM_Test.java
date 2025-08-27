package org.apache.commons.io.function;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IOSupplierLLM_Test {

    @FunctionalInterface
    interface TestIOSupplier<T> extends IOSupplier<T> {
        @Override
        T get() throws IOException;
    }

    @Test
    public void testGet() throws IOException {
        TestIOSupplier<String> supplier = () -> "test";
        assertEquals("test", supplier.get());
    }

    @Test
    public void testGetThrowsIOException() {
        TestIOSupplier<String> supplier = () -> {
            throw new IOException("IO error");
        };
        IOException exception = assertThrows(IOException.class, supplier::get);
        assertEquals("IO error", exception.getMessage());
    }
}