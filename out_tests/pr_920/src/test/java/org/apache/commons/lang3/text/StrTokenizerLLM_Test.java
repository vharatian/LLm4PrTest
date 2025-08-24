package org.apache.commons.lang3.text;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.AbstractLangTest;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Test;

@Deprecated
public class StrTokenizerLLM_Test extends AbstractLangTest {

    @Test
    public void testTrimmedCharacterHandling() {
        final String input = "a;b; c;\"d;\"\"e\";f; ; ;";
        final StrTokenizer tok = new StrTokenizer(input);
        tok.setDelimiterChar(';');
        tok.setQuoteChar('"');
        tok.setIgnoredMatcher(StrMatcher.trimMatcher());
        tok.setIgnoreEmptyTokens(false);
        final String[] tokens = tok.getTokenArray();
        final String[] expected = {"a", "b", "c", "d;\"e", "f", "", "", ""};
        assertEquals(expected.length, tokens.length, ArrayUtils.toString(tokens));
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], tokens[i],
                "token[" + i + "] was '" + tokens[i] + "' but was expected to be '" + expected[i] + "'");
        }
    }

    @Test
    public void testTrimmedCharacterHandlingWithQuotes() {
        final String input = "a;b; c;\"d;\"\"e\";f; ; ;";
        final StrTokenizer tok = new StrTokenizer(input);
        tok.setDelimiterChar(';');
        tok.setQuoteChar('"');
        tok.setIgnoredMatcher(StrMatcher.trimMatcher());
        tok.setIgnoreEmptyTokens(true);
        final String[] tokens = tok.getTokenArray();
        final String[] expected = {"a", "b", "c", "d;\"e", "f"};
        assertEquals(expected.length, tokens.length, ArrayUtils.toString(tokens));
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], tokens[i],
                "token[" + i + "] was '" + tokens[i] + "' but was expected to be '" + expected[i] + "'");
        }
    }

    @Test
    public void testTrimmedCharacterHandlingWithEmptyTokensAsNull() {
        final String input = "a;b; c;\"d;\"\"e\";f; ; ;";
        final StrTokenizer tok = new StrTokenizer(input);
        tok.setDelimiterChar(';');
        tok.setQuoteChar('"');
        tok.setIgnoredMatcher(StrMatcher.trimMatcher());
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        final String[] tokens = tok.getTokenArray();
        final String[] expected = {"a", "b", "c", "d;\"e", "f", null, null, null};
        assertEquals(expected.length, tokens.length, ArrayUtils.toString(tokens));
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], tokens[i],
                "token[" + i + "] was '" + tokens[i] + "' but was expected to be '" + expected[i] + "'");
        }
    }

    @Test
    public void testTrimmedCharacterHandlingWithQuotesAndEmptyTokensAsNull() {
        final String input = "a;b; c;\"d;\"\"e\";f; ; ;";
        final StrTokenizer tok = new StrTokenizer(input);
        tok.setDelimiterChar(';');
        tok.setQuoteChar('"');
        tok.setIgnoredMatcher(StrMatcher.trimMatcher());
        tok.setIgnoreEmptyTokens(false);
        tok.setEmptyTokenAsNull(true);
        final String[] tokens = tok.getTokenArray();
        final String[] expected = {"a", "b", "c", "d;\"e", "f", null, null, null};
        assertEquals(expected.length, tokens.length, ArrayUtils.toString(tokens));
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], tokens[i],
                "token[" + i + "] was '" + tokens[i] + "' but was expected to be '" + expected[i] + "'");
        }
    }
}