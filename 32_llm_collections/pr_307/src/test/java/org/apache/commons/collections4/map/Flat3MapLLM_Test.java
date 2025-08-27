package org.apache.commons.collections4.map;

import org.junit.jupiter.api.Test;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

public class Flat3MapLLM_Test<K, V> {

    @Test
    @SuppressWarnings("unchecked")
    public void testEqualsWithNullValues() {
        final Flat3Map<K, V> map1 = new Flat3Map<>();
        map1.put((K) "a", null);
        map1.put((K) "b", (V) "testB");
        final Flat3Map<K, V> map2 = new Flat3Map<>();
        map2.put((K) "a", null);
        map2.put((K) "b", (V) "testB");
        assertTrue(map1.equals(map2));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testEqualsWithDifferentNullValues() {
        final Flat3Map<K, V> map1 = new Flat3Map<>();
        map1.put((K) "a", null);
        map1.put((K) "b", (V) "testB");
        final Flat3Map<K, V> map2 = new Flat3Map<>();
        map2.put((K) "a", (V) "testA");
        map2.put((K) "b", (V) "testB");
        assertFalse(map1.equals(map2));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testEqualsWithObjectsEquals() {
        final Flat3Map<K, V> map1 = new Flat3Map<>();
        map1.put((K) "a", (V) "testA");
        map1.put((K) "b", (V) "testB");
        final Flat3Map<K, V> map2 = new Flat3Map<>();
        map2.put((K) "a", (V) "testA");
        map2.put((K) "b", (V) "testB");
        assertTrue(map1.equals(map2));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testEqualsWithObjectsNotEquals() {
        final Flat3Map<K, V> map1 = new Flat3Map<>();
        map1.put((K) "a", (V) "testA");
        map1.put((K) "b", (V) "testB");
        final Flat3Map<K, V> map2 = new Flat3Map<>();
        map2.put((K) "a", (V) "testA");
        map2.put((K) "b", (V) "testC");
        assertFalse(map1.equals(map2));
    }
}