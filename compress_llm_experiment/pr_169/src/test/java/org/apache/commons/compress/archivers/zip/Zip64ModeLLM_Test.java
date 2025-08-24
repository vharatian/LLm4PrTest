package org.apache.commons.compress.archivers.zip;

import org.junit.Test;
import static org.junit.Assert.*;

public class Zip64ModeLLM_Test {

    @Test
    public void testAlways() {
        assertEquals(Zip64Mode.Always, Zip64Mode.valueOf("Always"));
    }

    @Test
    public void testNever() {
        assertEquals(Zip64Mode.Never, Zip64Mode.valueOf("Never"));
    }

    @Test
    public void testAsNeeded() {
        assertEquals(Zip64Mode.AsNeeded, Zip64Mode.valueOf("AsNeeded"));
    }

    @Test
    public void testAlwaysWithCompatibility() {
        assertEquals(Zip64Mode.AlwaysWithCompatibility, Zip64Mode.valueOf("AlwaysWithCompatibility"));
    }
}