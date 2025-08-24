package org.apache.commons.lang3.concurrent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class BackgroundInitializerLLM_Test extends AbstractLangTest {

    @Test
    public void testBuilderSetExternalExecutorWithNull() {
        BackgroundInitializer.Builder<BackgroundInitializer<Object>, Object> builder = BackgroundInitializer.builder();
        builder.setExternalExecutor(null);
        BackgroundInitializer<Object> initializer = builder.get();
        assertEquals(null, initializer.getExternalExecutor(), "External executor should be null");
    }

    @Test
    public void testSetExternalExecutorWithNull() {
        AbstractBackgroundInitializerTestImpl init = new MethodBackgroundInitializerTestImpl();
        init.setExternalExecutor(null);
        assertEquals(null, init.getExternalExecutor(), "External executor should be null");
    }

    @Test
    public void testSetExternalExecutorAfterStartWithNull() throws ConcurrentException {
        AbstractBackgroundInitializerTestImpl init = new MethodBackgroundInitializerTestImpl();
        init.start();
        assertThrows(IllegalStateException.class, () -> init.setExternalExecutor(null));
    }
}