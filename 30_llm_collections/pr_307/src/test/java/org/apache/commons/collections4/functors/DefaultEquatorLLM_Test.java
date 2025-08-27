package org.apache.commons.collections4.functors;

import org.junit.Test;
import static org.junit.Assert.*;

public class DefaultEquatorLLM_Test {

    @Test
    public void testEquateWithEqualObjects() {
        DefaultEquator<String> equator = DefaultEquator.defaultEquator();
        String str1 = "test";
        String str2 = "test";
        assertTrue(equator.equate(str1, str2));
    }

    @Test
    public void testEquateWithDifferentObjects() {
        DefaultEquator<String> equator = DefaultEquator.defaultEquator();
        String str1 = "test1";
        String str2 = "test2";
        assertFalse(equator.equate(str1, str2));
    }

    @Test
    public void testEquateWithNullObjects() {
        DefaultEquator<String> equator = DefaultEquator.defaultEquator();
        String str1 = null;
        String str2 = null;
        assertTrue(equator.equate(str1, str2));
    }

    @Test
    public void testEquateWithOneNullObject() {
        DefaultEquator<String> equator = DefaultEquator.defaultEquator();
        String str1 = "test";
        String str2 = null;
        assertFalse(equator.equate(str1, str2));
    }

    @Test
    public void testHashWithNonNullObject() {
        DefaultEquator<String> equator = DefaultEquator.defaultEquator();
        String str = "test";
        assertEquals(str.hashCode(), equator.hash(str));
    }

    @Test
    public void testHashWithNullObject() {
        DefaultEquator<String> equator = DefaultEquator.defaultEquator();
        assertEquals(DefaultEquator.HASHCODE_NULL, equator.hash(null));
    }
}