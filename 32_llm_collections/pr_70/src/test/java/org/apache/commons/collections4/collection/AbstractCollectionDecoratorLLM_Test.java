package org.apache.commons.collections4.collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class AbstractCollectionDecoratorLLM_Test {

    private AbstractCollectionDecorator<String> decorator;
    private Collection<String> collection;

    @BeforeEach
    public void setUp() {
        collection = new ArrayList<>();
        collection.add("one");
        collection.add("two");
        collection.add("three");
        decorator = new AbstractCollectionDecorator<String>(collection) {};
    }

    @Test
    public void testRemoveIf() {
        Predicate<String> filter = s -> s.startsWith("t");
        boolean result = decorator.removeIf(filter);
        assertTrue(result);
        assertFalse(decorator.contains("two"));
        assertFalse(decorator.contains("three"));
        assertTrue(decorator.contains("one"));
    }
}