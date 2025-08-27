package org.apache.commons.io.file;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NoopPathVisitorLLM_Test {

    @Test
    public void testSingletonInstance() {
        // Test that INSTANCE is not null
        assertNotNull(NoopPathVisitor.INSTANCE, "INSTANCE should not be null");

        // Test that INSTANCE is of type NoopPathVisitor
        assertTrue(NoopPathVisitor.INSTANCE instanceof NoopPathVisitor, "INSTANCE should be of type NoopPathVisitor");

        // Test that INSTANCE is final by checking if it is the same instance every time
        NoopPathVisitor firstInstance = NoopPathVisitor.INSTANCE;
        NoopPathVisitor secondInstance = NoopPathVisitor.INSTANCE;
        assertSame(firstInstance, secondInstance, "INSTANCE should be the same across multiple accesses");
    }
}