package org.apache.commons.collections4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class SetUtilsLLM_Test {

    @Test
    public void testNewIdentityHashSet() {
        // Test to ensure the newIdentityHashSet method works correctly after the change
        final Set<String> set = SetUtils.newIdentityHashSet();
        final String a = new String("a");
        set.add(a);
        set.add(new String("b"));
        set.add(a);
        assertEquals(2, set.size());
        set.add(new String("a"));
        assertEquals(3, set.size());
        set.remove(a);
        assertEquals(2, set.size());
    }
}