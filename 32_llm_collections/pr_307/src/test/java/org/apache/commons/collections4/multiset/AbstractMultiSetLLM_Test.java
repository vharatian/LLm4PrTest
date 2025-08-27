package org.apache.commons.collections4.multiset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Objects;

import org.apache.commons.collections4.MultiSet;
import org.junit.jupiter.api.Test;

public abstract class AbstractMultiSetLLM_Test<T> extends AbstractMultiSetTest<T> {

    public AbstractMultiSetTest2(final String testName) {
        super(testName);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testMultiSetGetCountWithObjectsEquals() {
        if (!isAddSupported()) {
            return;
        }
        final MultiSet<T> multiset = makeObject();
        T element = (T) "A";
        multiset.add(element, 3);
        assertEquals(3, multiset.getCount(element));
        assertEquals(3, multiset.getCount("A"));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testMultiSetEqualsWithObjectsEquals() {
        if (!isAddSupported()) {
            return;
        }
        final MultiSet<T> multiset1 = makeObject();
        final MultiSet<T> multiset2 = makeObject();
        T element = (T) "A";
        multiset1.add(element, 2);
        multiset2.add((T) "A", 2);
        assertTrue(multiset1.equals(multiset2));
        assertTrue(Objects.equals(multiset1, multiset2));
    }
}