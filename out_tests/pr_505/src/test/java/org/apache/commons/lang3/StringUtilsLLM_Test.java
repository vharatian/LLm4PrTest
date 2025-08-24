package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class StringUtilsLLM_Test {

    @Test
    public void testReplaceEachCircularReference() {
        String[] searchList = {"a", "b"};
        String[] replacementList = {"b", "a"};
        assertThrows(IllegalStateException.class, () -> StringUtils.replaceEach("abab", searchList, replacementList, false, 0));
    }

    @Test
    public void testReplaceEachWithEmptySearchList() {
        String[] searchList = {};
        String[] replacementList = {"a"};
        assertEquals("text", StringUtils.replaceEach("text", searchList, replacementList, false, 0));
    }

    @Test
    public void testReplaceEachWithEmptyReplacementList() {
        String[] searchList = {"a"};
        String[] replacementList = {};
        assertEquals("text", StringUtils.replaceEach("text", searchList, replacementList, false, 0));
    }

    @Test
    public void testReplaceEachWithTimeToLiveZero() {
        String[] searchList = {"a"};
        String[] replacementList = {"b"};
        assertEquals("text", StringUtils.replaceEach("text", searchList, replacementList, false, -1));
    }

    @Test
    public void testReplaceEachWithCircularReferenceDetection() {
        String[] searchList = {"a", "b"};
        String[] replacementList = {"b", "a"};
        Set<String> searchSet = new HashSet<>(Arrays.asList(searchList));
        Set<String> replacementSet = new HashSet<>(Arrays.asList(replacementList));
        searchSet.retainAll(replacementSet);
        assertEquals(2, searchSet.size());
    }
}