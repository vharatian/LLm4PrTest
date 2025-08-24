package org.apache.commons.lang3.compare;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

public class ObjectToStringComparatorLLM_Test {

    @Test
    public void testCompareBothNull() {
        Comparator<Object> comparator = ObjectToStringComparator.INSTANCE;
        assertEquals(0, comparator.compare(null, null));
    }

    @Test
    public void testCompareFirstNull() {
        Comparator<Object> comparator = ObjectToStringComparator.INSTANCE;
        assertEquals(1, comparator.compare(null, "test"));
    }

    @Test
    public void testCompareSecondNull() {
        Comparator<Object> comparator = ObjectToStringComparator.INSTANCE;
        assertEquals(-1, comparator.compare("test", null));
    }

    @Test
    public void testCompareBothNonNull() {
        Comparator<Object> comparator = ObjectToStringComparator.INSTANCE;
        assertEquals("abc".compareTo("def"), comparator.compare("abc", "def"));
    }

    @Test
    public void testCompareFirstToStringNull() {
        Comparator<Object> comparator = ObjectToStringComparator.INSTANCE;
        Object obj1 = new Object() {
            @Override
            public String toString() {
                return null;
            }
        };
        Object obj2 = "test";
        assertEquals(1, comparator.compare(obj1, obj2));
    }

    @Test
    public void testCompareSecondToStringNull() {
        Comparator<Object> comparator = ObjectToStringComparator.INSTANCE;
        Object obj1 = "test";
        Object obj2 = new Object() {
            @Override
            public String toString() {
                return null;
            }
        };
        assertEquals(-1, comparator.compare(obj1, obj2));
    }

    @Test
    public void testCompareBothToStringNull() {
        Comparator<Object> comparator = ObjectToStringComparator.INSTANCE;
        Object obj1 = new Object() {
            @Override
            public String toString() {
                return null;
            }
        };
        Object obj2 = new Object() {
            @Override
            public String toString() {
                return null;
            }
        };
        assertEquals(0, comparator.compare(obj1, obj2));
    }
}