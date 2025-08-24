package org.apache.commons.lang3.function;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import java.util.function.Consumer;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class FailableLLM_Test {

    @Test
    public void testConsumerWithMethod() {
        Consumer<Method> consumer = m -> {
            try {
                m.invoke(null);
            } catch (Throwable t) {
                throw Failable.rethrow(t);
            }
        };

        assertDoesNotThrow(() -> {
            Method method = String.class.getMethod("valueOf", Object.class);
            consumer.accept(method);
        });
    }
}