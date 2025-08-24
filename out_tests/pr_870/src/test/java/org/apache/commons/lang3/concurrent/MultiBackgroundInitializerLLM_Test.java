package org.apache.commons.lang3.concurrent;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class MultiBackgroundInitializerLLM_Test {

    private MultiBackgroundInitializer initializer = new MultiBackgroundInitializer();

    /**
     * Test to ensure that addInitializer throws NullPointerException
     * when the name parameter is null.
     */
    @Test
    public void testAddInitializerNullName() {
        assertThrows(NullPointerException.class, () -> initializer.addInitializer(null, new MultiBackgroundInitializerTest.ChildBackgroundInitializer()));
    }

    /**
     * Test to ensure that addInitializer throws NullPointerException
     * when the backgroundInitializer parameter is null.
     */
    @Test
    public void testAddInitializerNullInit() {
        assertThrows(NullPointerException.class, () -> initializer.addInitializer("childInitializer", null));
    }
}