package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

public class StringUtilsLLM_Test {

    @Test
    public void testIndexOfAny_CharSequenceArray() {
        assertEquals(-1, StringUtils.indexOfAny(null, (CharSequence[]) null));
        assertEquals(-1, StringUtils.indexOfAny(null, ""));
        assertEquals(-1, StringUtils.indexOfAny("", (CharSequence[]) null));
        assertEquals(-1, StringUtils.indexOfAny("", ""));
        assertEquals(0, StringUtils.indexOfAny("zzabyycdxx", "za"));
        assertEquals(3, StringUtils.indexOfAny("zzabyycdxx", "by"));
        assertEquals(-1, StringUtils.indexOfAny("ab", "cd"));
        assertEquals(0, StringUtils.indexOfAny("zzabyycdxx", ""));
        assertEquals(-1, StringUtils.indexOfAny("zzabyycdxx", (CharSequence) null));
        assertEquals(-1, StringUtils.indexOfAny("zzabyycdxx", (CharSequence[]) null));
    }

    @Test
    public void testLastIndexOfAny_CharSequenceArray() {
        assertEquals(-1, StringUtils.lastIndexOfAny(null, (CharSequence[]) null));
        assertEquals(-1, StringUtils.lastIndexOfAny(null, ""));
        assertEquals(-1, StringUtils.lastIndexOfAny("", (CharSequence[]) null));
        assertEquals(-1, StringUtils.lastIndexOfAny("", ""));
        assertEquals(3, StringUtils.lastIndexOfAny("zzabyycdxx", "za"));
        assertEquals(4, StringUtils.lastIndexOfAny("zzabyycdxx", "by"));
        assertEquals(-1, StringUtils.lastIndexOfAny("ab", "cd"));
        assertEquals(9, StringUtils.lastIndexOfAny("zzabyycdxx", ""));
        assertEquals(-1, StringUtils.lastIndexOfAny("zzabyycdxx", (CharSequence) null));
        assertEquals(-1, StringUtils.lastIndexOfAny("zzabyycdxx", (CharSequence[]) null));
    }

    @Test
    public void testReplaceEach_TempIndexInitialization() {
        assertEquals("bcc", StringUtils.replaceEach("abc", new String[]{"a", "b"}, new String[]{"b", "c"}));
        assertEquals("bcc", StringUtils.replaceEach("abc", new String[]{"a", "b"}, new String[]{"b", "c"}, false, 0));
        assertEquals("bcc", StringUtils.replaceEach("abc", new String[]{"a", "b"}, new String[]{"b", "c"}, true, 0));
    }
}