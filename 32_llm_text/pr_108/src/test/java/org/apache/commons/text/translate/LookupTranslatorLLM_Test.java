package org.apache.commons.text.translate;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.StringWriter;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;
import java.util.BitSet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class LookupTranslatorLLM_Test {

    @Test
    public void testPrefixSetWithBitSet() throws IOException {
        final Map<CharSequence, CharSequence> translatorMap = new HashMap<>();
        translatorMap.put("one", "two");
        translatorMap.put("three", "four");
        final LookupTranslator lt = new LookupTranslator(translatorMap);
        final StringWriter out = new StringWriter();
        final int result = lt.translate("one", 0, out);
        assertThat(result).as("Incorrect codepoint consumption").isEqualTo(3);
        assertThat(out.toString()).as("Incorrect value").isEqualTo("two");
    }

    @Test
    public void testTranslateWithNonExistingPrefix() throws IOException {
        final Map<CharSequence, CharSequence> translatorMap = new HashMap<>();
        translatorMap.put("one", "two");
        final LookupTranslator lt = new LookupTranslator(translatorMap);
        final StringWriter out = new StringWriter();
        final int result = lt.translate("three", 0, out);
        assertThat(result).as("Incorrect codepoint consumption").isEqualTo(0);
        assertThat(out.toString()).as("Incorrect value").isEqualTo("");
    }

    @Test
    public void testTranslateWithPartialMatch() throws IOException {
        final Map<CharSequence, CharSequence> translatorMap = new HashMap<>();
        translatorMap.put("one", "two");
        translatorMap.put("on", "tw");
        final LookupTranslator lt = new LookupTranslator(translatorMap);
        final StringWriter out = new StringWriter();
        final int result = lt.translate("one", 0, out);
        assertThat(result).as("Incorrect codepoint consumption").isEqualTo(3);
        assertThat(out.toString()).as("Incorrect value").isEqualTo("two");
    }

    @Test
    public void testTranslateWithMultipleMatches() throws IOException {
        final Map<CharSequence, CharSequence> translatorMap = new HashMap<>();
        translatorMap.put("one", "two");
        translatorMap.put("o", "t");
        final LookupTranslator lt = new LookupTranslator(translatorMap);
        final StringWriter out = new StringWriter();
        final int result = lt.translate("one", 0, out);
        assertThat(result).as("Incorrect codepoint consumption").isEqualTo(3);
        assertThat(out.toString()).as("Incorrect value").isEqualTo("two");
    }
}