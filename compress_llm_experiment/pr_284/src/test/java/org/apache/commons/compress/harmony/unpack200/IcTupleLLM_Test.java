package org.apache.commons.compress.harmony.unpack200;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IcTupleLLM_Test {

    @Test
    public void testToString() {
        IcTuple icTuple = new IcTuple("C", 1, "C2", "N", 0, 1, 2, 3);
        String expected = "IcTuple (N in C2)";
        assertEquals(expected, icTuple.toString());
    }

    @Test
    public void testToStringWithNullValues() {
        IcTuple icTuple = new IcTuple(null, 1, null, null, 0, 1, 2, 3);
        String expected = "IcTuple (null in null)";
        assertEquals(expected, icTuple.toString());
    }
}