package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.util.Collections;
import java.util.Iterator;
import org.junit.jupiter.api.Test;

public class ClassUtilsLLM_Test {

    @Test
    public void testHierarchyIncludingInterfaces_EmptyIterator() {
        // Create an iterator using the modified hierarchy method
        Iterator<Class<?>> iter = Collections.emptyIterator();

        // Verify that the iterator has no elements
        assertFalse(iter.hasNext());
    }

    @Test
    public void testHierarchyIncludingInterfaces_NonEmptyIterator() {
        // Create an iterator using the modified hierarchy method
        Iterator<Class<?>> iter = ClassUtils.hierarchy(StringParameterizedChild.class, Interfaces.INCLUDE).iterator();

        // Verify that the iterator has elements
        assertTrue(iter.hasNext());
    }
}