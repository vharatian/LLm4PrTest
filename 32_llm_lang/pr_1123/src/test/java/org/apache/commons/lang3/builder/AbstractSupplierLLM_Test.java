package org.apache.commons.lang3.builder;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AbstractSupplierLLM_Test {

    private static class TestSupplier extends AbstractSupplier<String, TestSupplier, Exception> {
        @Override
        public String get() throws Exception {
            return "test";
        }
    }

    @Test
    void testAsThis() {
        TestSupplier supplier = new TestSupplier();
        assertEquals(supplier, supplier.asThis());
    }

    @Test
    void testGet() throws Exception {
        TestSupplier supplier = new TestSupplier();
        assertEquals("test", supplier.get());
    }
}