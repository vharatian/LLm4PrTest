package org.apache.commons.lang3.concurrent;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MultiBackgroundInitializerLLM_Test {
    private MultiBackgroundInitializer initializer;

    @BeforeEach
    public void setUp() {
        initializer = new MultiBackgroundInitializer();
    }

    @Test
    public void testCloseNoChildren() throws ConcurrentException {
        initializer.close();
    }

    @Test
    public void testCloseWithChildren() throws ConcurrentException {
        final ChildBackgroundInitializer child1 = new ChildBackgroundInitializer();
        final ChildBackgroundInitializer child2 = new ChildBackgroundInitializer();
        initializer.addInitializer("child1", child1);
        initializer.addInitializer("child2", child2);
        initializer.start();
        initializer.get();
        initializer.close();
        assertTrue(child1.isClosed(), "Child 1 not closed");
        assertTrue(child2.isClosed(), "Child 2 not closed");
    }

    @Test
    public void testCloseWithException() throws ConcurrentException {
        final ChildBackgroundInitializer child1 = new ChildBackgroundInitializer();
        final ChildBackgroundInitializer child2 = new ChildBackgroundInitializer();
        child2.setException(new ConcurrentException(new Exception("Test exception")));
        initializer.addInitializer("child1", child1);
        initializer.addInitializer("child2", child2);
        initializer.start();
        initializer.get();
        ConcurrentException ex = assertThrows(ConcurrentException.class, () -> initializer.close());
        assertTrue(ex.getSuppressed().length == 1, "Expected one suppressed exception");
        assertTrue(ex.getSuppressed()[0].getMessage().equals("Test exception"), "Unexpected suppressed exception message");
    }

    private static final class ChildBackgroundInitializer extends BackgroundInitializer<Integer> {
        private volatile boolean closed = false;
        private Exception ex;

        public void setException(Exception ex) {
            this.ex = ex;
        }

        public boolean isClosed() {
            return closed;
        }

        @Override
        protected Integer initialize() throws Exception {
            if (ex != null) {
                throw ex;
            }
            return 1;
        }

        @Override
        public void close() {
            closed = true;
        }
    }
}