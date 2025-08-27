package org.apache.commons.codec.language.bm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Test;

public class RuleLLM_Test {

    private static class PositiveIntegerBaseMatcher extends BaseMatcher<Integer> {
        @Override
        public void describeTo(final Description description) {
            description.appendText("value should be positive");
        }

        @Override
        public boolean matches(final Object item) {
            return ((Integer) item).intValue() > 0;
        }
    }

    private Rule.Phoneme[][] makePhonemes() {
        final String[][] words = {
            { "rinD", "rinDlt", "rina", "rinalt", "rino", "rinolt", "rinu", "rinult" },
            { "dortlaj", "dortlej", "ortlaj", "ortlej", "ortlej-dortlaj" }
        };
        final Rule.Phoneme[][] phonemes = new Rule.Phoneme[words.length][];
        for (int i = 0; i < words.length; i++) {
            final String[] words_i = words[i];
            final Rule.Phoneme[] phonemes_i = phonemes[i] = new Rule.Phoneme[words_i.length];
            for (int j = 0; j < words_i.length; j++) {
                phonemes_i[j] = new Rule.Phoneme(words_i[j], Languages.NO_LANGUAGES);
            }
        }
        return phonemes;
    }

    @Test
    public void testPhonemeComparedToEarlierIsPositive() {
        for (final Rule.Phoneme[] phs : makePhonemes()) {
            for (int i = 0; i < phs.length; i++) {
                for (int j = i + 1; j < phs.length; j++) {
                    final int c = Rule.Phoneme.COMPARATOR.compare(phs[j], phs[i]);
                    assertThat("Comparing " + phs[j].getPhonemeText() + " to " + phs[i].getPhonemeText() + " should be positive", Integer.valueOf(c),
                            new PositiveIntegerBaseMatcher());
                }
            }
        }
    }

    @Test
    public void testEndsWithFunction() {
        assertEquals(true, Rule.endsWith("testString", "String"));
        assertEquals(false, Rule.endsWith("testString", "str"));
        assertEquals(true, Rule.endsWith("test", "t"));
        assertEquals(false, Rule.endsWith("test", "testString"));
    }

    @Test
    public void testHashIncludeLength() {
        final String line = "#include somefile";
        final String incl = line.substring(Rule.HASH_INCLUDE_LENGTH).trim();
        assertEquals("somefile", incl);
    }

    @Test
    public void testPatternFunction() {
        Rule.RPattern pattern = Rule.pattern("^test$");
        assertEquals(true, pattern.isMatch("test"));
        assertEquals(false, pattern.isMatch("testString"));

        pattern = Rule.pattern("^test");
        assertEquals(true, pattern.isMatch("testString"));
        assertEquals(false, pattern.isMatch("Stringtest"));

        pattern = Rule.pattern("test$");
        assertEquals(true, pattern.isMatch("Stringtest"));
        assertEquals(false, pattern.isMatch("testString"));

        pattern = Rule.pattern("");
        assertEquals(true, pattern.isMatch(""));
        assertEquals(false, pattern.isMatch("notEmpty"));
    }
}