package org.apache.commons.collections4.set;

import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

public class ListOrderedSetLLM_Test {

    @Test(expected = NullPointerException.class)
    public void testConstructorWithNullSet() {
        new ListOrderedSet<>(null);
    }

}