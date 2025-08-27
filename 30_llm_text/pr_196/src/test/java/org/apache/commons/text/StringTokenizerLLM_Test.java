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
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.matcher.StringMatcher;
import org.apache.commons.text.matcher.StringMatcherFactory;
import org.junit.jupiter.api.Test;

public class StringTokenizerLLM_Test {

    @Test
    public void testCheckTokenizedWithNullChars() {
        StringTokenizer tokenizer = new StringTokenizer();
        tokenizer.checkTokenized();
        assertEquals(0, tokenizer.size());
        assertEquals(ArrayUtils.EMPTY_STRING_ARRAY, tokenizer.getTokenArray());
    }

    @Test
    public void testCheckTokenizedWithEmptyChars() {
        StringTokenizer tokenizer = new StringTokenizer("");
        tokenizer.checkTokenized();
        assertEquals(1, tokenizer.size());
        assertEquals(ArrayUtils.EMPTY_STRING_ARRAY, tokenizer.getTokenArray());
    }

    @Test
    public void testCheckTokenizedWithNonEmptyChars() {
        StringTokenizer tokenizer = new StringTokenizer("a b c");
        tokenizer.checkTokenized();
        assertEquals(3, tokenizer.size());
        assertEquals(new String[]{"a", "b", "c"}, tokenizer.getTokenArray());
    }

    @Test
    public void testCheckTokenizedWithEmptyTokenAsNull() {
        StringTokenizer tokenizer = new StringTokenizer("a b c");
        tokenizer.setEmptyTokenAsNull(true);
        tokenizer.checkTokenized();
        assertEquals(3, tokenizer.size());
        assertEquals(new String[]{"a", "b", "c"}, tokenizer.getTokenArray());
    }

    @Test
    public void testCheckTokenizedWithIgnoreEmptyTokens() {
        StringTokenizer tokenizer = new StringTokenizer("a b c");
        tokenizer.setIgnoreEmptyTokens(true);
        tokenizer.checkTokenized();
        assertEquals(3, tokenizer.size());
        assertEquals(new String[]{"a", "b", "c"}, tokenizer.getTokenArray());
    }

    @Test
    public void testTokenizeWithNullSrcChars() {
        StringTokenizer tokenizer = new StringTokenizer();
        List<String> tokens = tokenizer.tokenize(null, 0, 0);
        assertEquals(Collections.emptyList(), tokens);
    }

    @Test
    public void testTokenizeWithEmptySrcChars() {
        StringTokenizer tokenizer = new StringTokenizer("");
        List<String> tokens = tokenizer.tokenize("".toCharArray(), 0, 0);
        assertEquals(Collections.emptyList(), tokens);
    }

    @Test
    public void testTokenizeWithNonEmptySrcChars() {
        StringTokenizer tokenizer = new StringTokenizer("a b c");
        List<String> tokens = tokenizer.tokenize("a b c".toCharArray(), 0, 5);
        assertEquals(Arrays.asList("a", "b", "c"), tokens);
    }

    @Test
    public void testTokenizeWithEmptyTokenAsNull() {
        StringTokenizer tokenizer = new StringTokenizer("a b c");
        tokenizer.setEmptyTokenAsNull(true);
        List<String> tokens = tokenizer.tokenize("a b c".toCharArray(), 0, 5);
        assertEquals(Arrays.asList("a", "b", "c"), tokens);
    }

    @Test
    public void testTokenizeWithIgnoreEmptyTokens() {
        StringTokenizer tokenizer = new StringTokenizer("a b c");
        tokenizer.setIgnoreEmptyTokens(true);
        List<String> tokens = tokenizer.tokenize("a b c".toCharArray(), 0, 5);
        assertEquals(Arrays.asList("a", "b", "c"), tokens);
    }

    @Test
    public void testCloneReset() throws CloneNotSupportedException {
        StringTokenizer tokenizer = new StringTokenizer("a b c");
        StringTokenizer clonedTokenizer = (StringTokenizer) tokenizer.cloneReset();
        assertEquals(tokenizer.getTokenArray(), clonedTokenizer.getTokenArray());
    }

    @Test
    public void testCloneResetWithNullChars() throws CloneNotSupportedException {
        StringTokenizer tokenizer = new StringTokenizer();
        StringTokenizer clonedTokenizer = (StringTokenizer) tokenizer.cloneReset();
        assertNull(clonedTokenizer.getTokenArray());
    }

    @Test
    public void testCloneResetWithEmptyChars() throws CloneNotSupportedException {
        StringTokenizer tokenizer = new StringTokenizer("");
        StringTokenizer clonedTokenizer = (StringTokenizer) tokenizer.cloneReset();
        assertEquals(tokenizer.getTokenArray(), clonedTokenizer.getTokenArray());
    }
}