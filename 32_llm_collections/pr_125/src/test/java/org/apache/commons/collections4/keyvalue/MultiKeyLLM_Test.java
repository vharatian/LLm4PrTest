package org.apache.commons.collections4.keyvalue;

import org.junit.Test;
import static org.junit.Assert.*;

public class MultiKeyLLM_Test {

    @Test(expected = NullPointerException.class)
    public void testConstructorWithNullArray() {
        new MultiKey<>(null);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorWithNullArrayAndClone() {
        new MultiKey<>(null, true);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorWithNullArrayAndNoClone() {
        new MultiKey<>(null, false);
    }
}