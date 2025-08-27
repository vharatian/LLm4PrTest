package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EquatorLLM_Test {

    @Test
    public void testEquate() {
        Equator<String> equator = new Equator<String>() {
            @Override
            public boolean equate(String o1, String o2) {
                return o1.equals(o2);
            }

            @Override
            public int hash(String o) {
                return o.hashCode();
            }
        };

        assertTrue(equator.equate("test", "test"));
        assertFalse(equator.equate("test", "different"));
    }

    @Test
    public void testHash() {
        Equator<String> equator = new Equator<String>() {
            @Override
            public boolean equate(String o1, String o2) {
                return o1.equals(o2);
            }

            @Override
            public int hash(String o) {
                return o.hashCode();
            }
        };

        assertEquals("test".hashCode(), equator.hash("test"));
        assertNotEquals("test".hashCode(), equator.hash("different"));
    }
}