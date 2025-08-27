package org.apache.commons.text.similarity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.util.List;

class RegexTokenizerLLM_Test {

    @Test
    void testTokenizeWithValidText() {
        RegexTokenizer tokenizer = new RegexTokenizer();
        CharSequence[] tokens = tokenizer.tokenize("This is a test");
        assertArrayEquals(new String[]{"This", "is", "a", "test"}, tokens);
    }

    @Test
    void testTokenizeWithEmptyText() {
        RegexTokenizer tokenizer = new RegexTokenizer();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            tokenizer.tokenize("");
        });
        assertEquals("Invalid text", exception.getMessage());
    }

    @Test
    void testTokenizeWithNullText() {
        RegexTokenizer tokenizer = new RegexTokenizer();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            tokenizer.tokenize(null);
        });
        assertEquals("Invalid text", exception.getMessage());
    }

    @Test
    void testTokenizeWithSpecialCharacters() {
        RegexTokenizer tokenizer = new RegexTokenizer();
        CharSequence[] tokens = tokenizer.tokenize("Hello, world! 123.");
        assertArrayEquals(new String[]{"Hello", "world", "123"}, tokens);
    }

    @Test
    void testTokenizeWithWhitespaceOnly() {
        RegexTokenizer tokenizer = new RegexTokenizer();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            tokenizer.tokenize("   ");
        });
        assertEquals("Invalid text", exception.getMessage());
    }
}