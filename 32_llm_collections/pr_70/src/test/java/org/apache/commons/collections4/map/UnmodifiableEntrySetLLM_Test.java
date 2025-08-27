package org.apache.commons.collections4.map;

import org.junit.Test;
import org.junit.Before;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import static org.junit.Assert.*;

public class UnmodifiableEntrySetLLM_Test {

    private Set<Map.Entry<String, String>> entrySet;
    private UnmodifiableEntrySet<String, String> unmodifiableEntrySet;

    @Before
    public void setUp() {
        entrySet = new HashSet<>();
        entrySet.add(new AbstractMap.SimpleEntry<>("key1", "value1"));
        entrySet.add(new AbstractMap.SimpleEntry<>("key2", "value2"));
        unmodifiableEntrySet = (UnmodifiableEntrySet<String, String>) UnmodifiableEntrySet.unmodifiableEntrySet(entrySet);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testRemoveIf() {
        Predicate<Map.Entry<String, String>> predicate = entry -> entry.getKey().equals("key1");
        unmodifiableEntrySet.removeIf(predicate);
    }
}