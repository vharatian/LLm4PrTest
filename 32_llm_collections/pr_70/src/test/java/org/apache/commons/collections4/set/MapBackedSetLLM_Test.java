package org.apache.commons.collections4.set;

import java.util.Set;
import java.util.function.Predicate;
import org.apache.commons.collections4.map.HashedMap;
import org.junit.Test;
import static org.junit.Assert.*;

public class MapBackedSetLLM_Test<E> extends AbstractSetTest<E> {
    
    public MapBackedSetTest2(final String testName) {
        super(testName);
    }

    @Override
    public Set<E> makeObject() {
        return MapBackedSet.mapBackedSet(new HashedMap<E, Object>());
    }

    @Override
    public String getCompatibilityVersion() {
        return "4";
    }

    @Test
    public void testRemoveIf() {
        Set<String> set = MapBackedSet.mapBackedSet(new HashedMap<String, Object>());
        set.add("one");
        set.add("two");
        set.add("three");

        Predicate<String> filter = s -> s.startsWith("t");
        boolean result = set.removeIf(filter);

        assertTrue(result);
        assertFalse(set.contains("two"));
        assertFalse(set.contains("three"));
        assertTrue(set.contains("one"));
    }
}