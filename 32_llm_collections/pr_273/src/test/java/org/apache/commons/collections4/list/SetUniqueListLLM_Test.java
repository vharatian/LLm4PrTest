package org.apache.commons.collections4.list;

import org.apache.commons.collections4.set.UnmodifiableSet;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

public class SetUniqueListLLM_Test<E> extends AbstractListTest<E> {

    @Test
    public void testSetUniqueListWithEmptyList() {
        List<String> emptyList = new ArrayList<>();
        SetUniqueList<String> uniqueList = SetUniqueList.setUniqueList(emptyList);
        assertTrue(uniqueList.isEmpty(), "The unique list should be empty.");
    }

    @Test
    public void testSetUniqueListWithNonEmptyList() {
        List<String> list = new ArrayList<>(Arrays.asList("One", "Two", "Three"));
        SetUniqueList<String> uniqueList = SetUniqueList.setUniqueList(list);
        assertEquals(3, uniqueList.size(), "The unique list should contain 3 elements.");
        assertTrue(uniqueList.contains("One"), "The unique list should contain 'One'.");
        assertTrue(uniqueList.contains("Two"), "The unique list should contain 'Two'.");
        assertTrue(uniqueList.contains("Three"), "The unique list should contain 'Three'.");
    }

    @Test
    public void testSetUniqueListWithDuplicates() {
        List<String> list = new ArrayList<>(Arrays.asList("One", "Two", "Two", "Three"));
        SetUniqueList<String> uniqueList = SetUniqueList.setUniqueList(list);
        assertEquals(3, uniqueList.size(), "The unique list should contain 3 elements.");
        assertTrue(uniqueList.contains("One"), "The unique list should contain 'One'.");
        assertTrue(uniqueList.contains("Two"), "The unique list should contain 'Two'.");
        assertTrue(uniqueList.contains("Three"), "The unique list should contain 'Three'.");
    }
}