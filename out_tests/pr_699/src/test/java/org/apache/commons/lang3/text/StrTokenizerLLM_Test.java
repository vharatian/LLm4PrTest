package org.apache.commons.lang3.text;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

@Deprecated
public class StrTokenizerLLM_Test {

    @Test
    public void testEmptyAsNullDefault() {
        // Test default value of emptyAsNull
        StrTokenizer tokenizer = new StrTokenizer();
        assertFalse(tokenizer.isEmptyTokenAsNull(), "Default value of emptyAsNull should be false");
    }

    @Test
    public void testEmptyAsNullTrue() {
        // Test setting emptyAsNull to true
        StrTokenizer tokenizer = new StrTokenizer("a b c");
        tokenizer.setEmptyTokenAsNull(true);
        assertTrue(tokenizer.isEmptyTokenAsNull(), "emptyAsNull should be true after setting it to true");
    }

    @Test
    public void testEmptyAsNullFalse() {
        // Test setting emptyAsNull to false
        StrTokenizer tokenizer = new StrTokenizer("a b c");
        tokenizer.setEmptyTokenAsNull(false);
        assertFalse(tokenizer.isEmptyTokenAsNull(), "emptyAsNull should be false after setting it to false");
    }

    @Test
    public void testEmptyAsNullWithEmptyTokens() {
        // Test behavior when emptyAsNull is true and there are empty tokens
        StrTokenizer tokenizer = new StrTokenizer("a b  c");
        tokenizer.setDelimiterChar(' ');
        tokenizer.setEmptyTokenAsNull(true);
        tokenizer.setIgnoreEmptyTokens(false);
        String[] tokens = tokenizer.getTokenArray();
        String[] expected = new String[]{"a", "b", null, "c"};
        assertEquals(expected.length, tokens.length, "Token array length mismatch");
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], tokens[i], "Token[" + i + "] mismatch");
        }
    }

    @Test
    public void testEmptyAsNullWithNonEmptyTokens() {
        // Test behavior when emptyAsNull is true and there are no empty tokens
        StrTokenizer tokenizer = new StrTokenizer("a b c");
        tokenizer.setDelimiterChar(' ');
        tokenizer.setEmptyTokenAsNull(true);
        tokenizer.setIgnoreEmptyTokens(false);
        String[] tokens = tokenizer.getTokenArray();
        String[] expected = new String[]{"a", "b", "c"};
        assertEquals(expected.length, tokens.length, "Token array length mismatch");
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], tokens[i], "Token[" + i + "] mismatch");
        }
    }

    @Test
    public void testEmptyAsNullWithIgnoreEmptyTokens() {
        // Test behavior when emptyAsNull is true and ignoreEmptyTokens is true
        StrTokenizer tokenizer = new StrTokenizer("a b  c");
        tokenizer.setDelimiterChar(' ');
        tokenizer.setEmptyTokenAsNull(true);
        tokenizer.setIgnoreEmptyTokens(true);
        String[] tokens = tokenizer.getTokenArray();
        String[] expected = new String[]{"a", "b", "c"};
        assertEquals(expected.length, tokens.length, "Token array length mismatch");
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], tokens[i], "Token[" + i + "] mismatch");
        }
    }
}