package org.apache.commons.collections4.bag;

import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import static org.junit.Assert.*;

public class AbstractMapBagLLM_Test {

    private AbstractMapBag<String> bag;

    @Before
    public void setUp() {
        Map<String, AbstractMapBag.MutableInteger> map = new HashMap<>();
        bag = new AbstractMapBag<String>(map) {};
    }

    @Test
    public void testBagIteratorConstructor() {
        AbstractMapBag.BagIterator<String> iterator = new AbstractMapBag.BagIterator<>(bag);
        assertNotNull(iterator);
    }

    @Test
    public void testUniqueSet() {
        bag.add("apple", 2);
        bag.add("banana", 3);
        Set<String> uniqueSet = bag.uniqueSet();
        assertNotNull(uniqueSet);
        assertEquals(2, uniqueSet.size());
        assertTrue(uniqueSet.contains("apple"));
        assertTrue(uniqueSet.contains("banana"));
    }
}