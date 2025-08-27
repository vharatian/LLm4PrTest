package org.apache.commons.text;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Test;

@Deprecated
public class StrTokenizerLLM_Test {

    @Test
    public void testCheckTokenizedWithNullChars() {
        StrTokenizer tokenizer = new StrTokenizer();
        tokenizer.checkTokenized();
        assertEquals(0, tokenizer.size());
        assertEquals(ArrayUtils.EMPTY_STRING_ARRAY, tokenizer.getTokenArray());
    }

    @Test
    public void testCheckTokenizedWithEmptyChars() {
        StrTokenizer tokenizer = new StrTokenizer(ArrayUtils.EMPTY_CHAR_ARRAY);
        tokenizer.checkTokenized();
        assertEquals(0, tokenizer.size());
        assertEquals(ArrayUtils.EMPTY_STRING_ARRAY, tokenizer.getTokenArray());
    }

    @Test
    public void testCheckTokenizedWithNonEmptyChars() {
        StrTokenizer tokenizer = new StrTokenizer("a b c".toCharArray());
        tokenizer.checkTokenized();
        assertEquals(3, tokenizer.size());
        assertEquals(new String[]{"a", "b", "c"}, tokenizer.getTokenArray());
    }

    @Test
    public void testConstructorDefaultValues() {
        StrTokenizer tokenizer = new StrTokenizer();
        assertFalse(tokenizer.isEmptyTokenAsNull());
        assertTrue(tokenizer.isIgnoreEmptyTokens());
    }

    @Test
    public void testConstructorWithEmptyTokenAsNull() {
        StrTokenizer tokenizer = new StrTokenizer();
        tokenizer.setEmptyTokenAsNull(true);
        assertTrue(tokenizer.isEmptyTokenAsNull());
    }

    @Test
    public void testConstructorWithIgnoreEmptyTokens() {
        StrTokenizer tokenizer = new StrTokenizer();
        tokenizer.setIgnoreEmptyTokens(false);
        assertFalse(tokenizer.isIgnoreEmptyTokens());
    }
}