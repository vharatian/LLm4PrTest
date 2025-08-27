package org.apache.commons.collections4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.functors.DefaultEquator;
import org.junit.jupiter.api.Test;

public class CollectionUtilsLLM_Test {

    @Test
    public void testCollectWithIterator() {
        // Test for the change in collect method with Iterator
        List<Integer> inputList = new ArrayList<>();
        inputList.add(1);
        inputList.add(2);
        inputList.add(3);
        Iterator<Integer> iterator = inputList.iterator();
        List<String> result = CollectionUtils.collect(iterator, input -> "Number: " + input);
        assertEquals(3, result.size());
        assertTrue(result.contains("Number: 1"));
        assertTrue(result.contains("Number: 2"));
        assertTrue(result.contains("Number: 3"));
    }

    @Test
    public void testRetainAllWithEquator() {
        // Test for the change in retainAll method with Equator
        List<String> base = new ArrayList<>();
        base.add("AC");
        base.add("BB");
        base.add("CA");
        List<String> retain = new ArrayList<>();
        retain.add("AA");
        retain.add("CX");
        retain.add("XZ");
        Set<String> result = CollectionUtils.retainAll(base, retain, new DefaultEquator<>());
        assertEquals(1, result.size());
        assertTrue(result.contains("CA"));
    }

    @Test
    public void testRemoveAllWithEquator() {
        // Test for the change in removeAll method with Equator
        List<String> base = new ArrayList<>();
        base.add("AC");
        base.add("BB");
        base.add("CA");
        List<String> remove = new ArrayList<>();
        remove.add("AA");
        remove.add("CX");
        remove.add("XZ");
        Set<String> result = CollectionUtils.removeAll(base, remove, new DefaultEquator<>());
        assertEquals(2, result.size());
        assertTrue(result.contains("AC"));
        assertTrue(result.contains("BB"));
    }

    @Test
    public void testRetainAllWithNullEquator() {
        // Test for retainAll method with null Equator
        List<String> base = new ArrayList<>();
        base.add("AC");
        base.add("BB");
        base.add("CA");
        List<String> retain = new ArrayList<>();
        retain.add("AA");
        retain.add("CX");
        retain.add("XZ");
        assertThrows(NullPointerException.class, () -> CollectionUtils.retainAll(base, retain, null));
    }

    @Test
    public void testRemoveAllWithNullEquator() {
        // Test for removeAll method with null Equator
        List<String> base = new ArrayList<>();
        base.add("AC");
        base.add("BB");
        base.add("CA");
        List<String> remove = new ArrayList<>();
        remove.add("AA");
        remove.add("CX");
        remove.add("XZ");
        assertThrows(NullPointerException.class, () -> CollectionUtils.removeAll(base, remove, null));
    }
}