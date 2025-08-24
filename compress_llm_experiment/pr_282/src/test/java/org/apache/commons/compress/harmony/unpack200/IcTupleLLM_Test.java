package org.apache.commons.compress.harmony.unpack200;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IcTupleLLM_Test {

    @Test
    public void testInnerBreakAtDollar() {
        IcTuple tuple = new IcTuple("Test$Inner", 0, null, null, 0, 0, 0, 0);
        String[] result = tuple.innerBreakAtDollar("Test$Inner");
        assertArrayEquals(new String[]{"Test", "Inner"}, result);
    }

    @Test
    public void testInnerBreakAtDollarWithNoDollar() {
        IcTuple tuple = new IcTuple("Test", 0, null, null, 0, 0, 0, 0);
        String[] result = tuple.innerBreakAtDollar("Test");
        assertArrayEquals(new String[]{"Test"}, result);
    }

    @Test
    public void testInnerBreakAtDollarWithMultipleDollars() {
        IcTuple tuple = new IcTuple("Test$Inner$MostInner", 0, null, null, 0, 0, 0, 0);
        String[] result = tuple.innerBreakAtDollar("Test$Inner$MostInner");
        assertArrayEquals(new String[]{"Test", "Inner", "MostInner"}, result);
    }

    @Test
    public void testInitializeClassStrings() {
        IcTuple tuple = new IcTuple("Test$Inner", 0, "Test", "Inner", 0, 1, 2, 3);
        assertEquals("Test", tuple.outerClassString());
        assertEquals("Inner", tuple.simpleClassName());
    }

    @Test
    public void testInitializeClassStringsWithNoDollar() {
        IcTuple tuple = new IcTuple("Test", 0, null, null, 0, 0, 0, 0);
        assertEquals("", tuple.outerClassString());
        assertEquals("Test", tuple.simpleClassName());
    }

    @Test
    public void testInitializeClassStringsWithMultipleDollars() {
        IcTuple tuple = new IcTuple("Test$Inner$MostInner", 0, "Test$Inner", "MostInner", 0, 1, 2, 3);
        assertEquals("Test$Inner", tuple.outerClassString());
        assertEquals("MostInner", tuple.simpleClassName());
    }
}