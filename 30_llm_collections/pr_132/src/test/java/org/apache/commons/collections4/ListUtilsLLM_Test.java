package org.apache.commons.collections4;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Test;

public class ListUtilsLLM_Test {

    @Test
    public void testLcsVisitorConstructor() {
        ListUtils.LcsVisitor<String> visitor = new ListUtils.LcsVisitor<>();
        assertNotNull(visitor);
        assertTrue(visitor.getSubSequence().isEmpty());
    }

    @Test
    public void testCharSequenceAsListConstructor() {
        CharSequence sequence = "test";
        ListUtils.CharSequenceAsList charSequenceAsList = new ListUtils.CharSequenceAsList(sequence);
        assertNotNull(charSequenceAsList);
        assertEquals(4, charSequenceAsList.size());
        assertEquals(Character.valueOf('t'), charSequenceAsList.get(0));
    }
}