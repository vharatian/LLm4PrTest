package org.apache.commons.compress.harmony.pack200;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CPIntLLM_Test {

    @Test
    public void testCompareToGreaterThan() {
        CPInt cpInt1 = new CPInt(10);
        CPInt cpInt2 = new CPInt(5);
        assertTrue(cpInt1.compareTo(cpInt2) > 0);
    }

    @Test
    public void testCompareToLessThan() {
        CPInt cpInt1 = new CPInt(5);
        CPInt cpInt2 = new CPInt(10);
        assertTrue(cpInt1.compareTo(cpInt2) < 0);
    }

    @Test
    public void testCompareToEqual() {
        CPInt cpInt1 = new CPInt(10);
        CPInt cpInt2 = new CPInt(10);
        assertEquals(0, cpInt1.compareTo(cpInt2));
    }

    @Test
    public void testGetInt() {
        CPInt cpInt = new CPInt(10);
        assertEquals(10, cpInt.getInt());
    }
}