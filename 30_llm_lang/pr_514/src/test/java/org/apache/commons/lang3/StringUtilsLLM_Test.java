package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

public class StringUtilsLLM_Test {

    @Test
    public void testReplaceEach_withNullAndEmptySearchList() {
        assertEquals("abcde", StringUtils.replaceEach("abcde", new String[]{null}, new String[]{"x"}));
        assertEquals("abcde", StringUtils.replaceEach("abcde", new String[]{""}, new String[]{"x"}));
        assertEquals("abcde", StringUtils.replaceEach("abcde", new String[]{null, ""}, new String[]{"x", "y"}));
    }

    @Test
    public void testReplaceEach_withEmptySearchList() {
        assertEquals("abcde", StringUtils.replaceEach("abcde", new String[]{""}, new String[]{"x"}));
        assertEquals("abcde", StringUtils.replaceEach("abcde", new String[]{"", null}, new String[]{"x", "y"}));
    }

    @Test
    public void testReplaceEach_withNullReplacementList() {
        assertEquals("abcde", StringUtils.replaceEach("abcde", new String[]{"a"}, new String[]{null}));
        assertEquals("abcde", StringUtils.replaceEach("abcde", new String[]{"a", "b"}, new String[]{null, null}));
    }

    @Test
    public void testReplaceEach_withEmptyReplacementList() {
        assertEquals("bcde", StringUtils.replaceEach("abcde", new String[]{"a"}, new String[]{""}));
        assertEquals("cde", StringUtils.replaceEach("abcde", new String[]{"a", "b"}, new String[]{"", ""}));
    }
}