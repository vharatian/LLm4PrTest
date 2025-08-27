package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MultiValuedMapLLM_Test {

    @Test
    public void testExampleUsage() {
        MultiValuedMap<Integer, String> map = new ArrayListValuedHashMap<>();
        map.put(1, "A");
        map.put(1, "B");
        map.put(1, "C");
        Collection<String> coll = map.get(1);

        assertNotNull(coll);
        assertEquals(3, coll.size());
        assertTrue(coll.contains("A"));
        assertTrue(coll.contains("B"));
        assertTrue(coll.contains("C"));
    }
}