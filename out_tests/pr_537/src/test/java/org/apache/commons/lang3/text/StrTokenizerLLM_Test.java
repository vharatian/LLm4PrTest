package org.apache.commons.lang3.text;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.commons.lang3.ArrayUtils;

@Deprecated
public class StrTokenizerLLM_Test {

    @Test
    public void testArrayFieldInitialization() {
        // Test to ensure that the char[] and String[] fields are correctly initialized
        StrTokenizer tokenizer = new StrTokenizer();
        assertNull(tokenizer.getContent());
        assertEquals(0, tokenizer.size());
    }

    @Test
    public void testArrayFieldReset() {
        // Test to ensure that the char[] and String[] fields are correctly reset
        StrTokenizer tokenizer = new StrTokenizer("a b c");
        assertEquals("a", tokenizer.next());
        tokenizer.reset();
        assertEquals("a", tokenizer.next());
        tokenizer.reset("d e f");
        assertEquals("d", tokenizer.next());
    }

    @Test
    public void testCloneArrayFields() {
        // Test to ensure that the char[] and String[] fields are correctly cloned
        char[] input = new char[]{'a', 'b', 'c'};
        StrTokenizer tokenizer = new StrTokenizer(input);
        StrTokenizer clonedTokenizer = (StrTokenizer) tokenizer.clone();
        input[0] = 'z';
        tokenizer.reset(input);
        assertEquals("zbc", tokenizer.next());
        assertEquals("abc", clonedTokenizer.next());
    }

    @Test
    public void testArrayFieldIteration() {
        // Test to ensure that the char[] and String[] fields are correctly iterated
        StrTokenizer tokenizer = new StrTokenizer("a b c");
        assertEquals("a", tokenizer.next());
        assertEquals("b", tokenizer.next());
        assertEquals("c", tokenizer.next());
        assertFalse(tokenizer.hasNext());
        assertTrue(tokenizer.hasPrevious());
        assertEquals("c", tokenizer.previous());
        assertEquals("b", tokenizer.previous());
        assertEquals("a", tokenizer.previous());
        assertFalse(tokenizer.hasPrevious());
    }

    @Test
    public void testArrayFieldEmptyInput() {
        // Test to ensure that the char[] and String[] fields handle empty input correctly
        StrTokenizer tokenizer = new StrTokenizer("");
        assertFalse(tokenizer.hasNext());
        assertFalse(tokenizer.hasPrevious());
        assertNull(tokenizer.nextToken());
        assertEquals(0, tokenizer.size());
        assertThrows(NoSuchElementException.class, tokenizer::next);
    }
}