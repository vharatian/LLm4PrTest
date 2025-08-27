package org.apache.commons.collections4.multimap;

import java.util.Collection;
import org.apache.commons.collections4.MultiValuedMap;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AbstractMultiValuedMapLLM_Test {

    private MultiValuedMap<String, String> map;

    @Before
    public void setUp() {
        map = new ArrayListValuedHashMap<>();
        map.put("one", "uno");
        map.put("one", "un");
        map.put("two", "dos");
        map.put("two", "deux");
        map.put("three", "tres");
        map.put("three", "trois");
    }

    @Test
    public void testWrappedCollectionContains() {
        Collection<String> collection = map.get("one");
        assertTrue(collection.contains("uno"));
        assertTrue(collection.contains("un"));
        assertFalse(collection.contains("dos"));
    }

    @Test
    public void testWrappedCollectionContainsAll() {
        Collection<String> collection = map.get("one");
        assertTrue(collection.containsAll(CollectionUtils.arrayToList(new String[]{"uno", "un"})));
        assertFalse(collection.containsAll(CollectionUtils.arrayToList(new String[]{"uno", "dos"})));
    }

    @Test
    public void testWrappedCollectionIsEmpty() {
        Collection<String> collection = map.get("one");
        assertFalse(collection.isEmpty());

        collection = map.get("four");
        assertTrue(collection.isEmpty());
    }
}