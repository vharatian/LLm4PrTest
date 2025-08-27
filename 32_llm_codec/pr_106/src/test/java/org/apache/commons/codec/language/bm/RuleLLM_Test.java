package org.apache.commons.codec.language.bm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RuleLLM_Test {

    @Test
    public void testPhonemeComparator() {
        Rule.Phoneme phoneme1 = new Rule.Phoneme("a", Languages.NO_LANGUAGES);
        Rule.Phoneme phoneme2 = new Rule.Phoneme("b", Languages.NO_LANGUAGES);
        Rule.Phoneme phoneme3 = new Rule.Phoneme("a", Languages.NO_LANGUAGES);

        // Test comparator for different phonemes
        assertTrue(Rule.Phoneme.COMPARATOR.compare(phoneme1, phoneme2) < 0);
        assertTrue(Rule.Phoneme.COMPARATOR.compare(phoneme2, phoneme1) > 0);

        // Test comparator for same phonemes
        assertEquals(0, Rule.Phoneme.COMPARATOR.compare(phoneme1, phoneme3));
    }

    @Test
    public void testAllStringsRMatcher() {
        Rule.RPattern allStringsMatcher = Rule.ALL_STRINGS_RMATCHER;

        // Test that it matches any input
        assertTrue(allStringsMatcher.isMatch(""));
        assertTrue(allStringsMatcher.isMatch("any string"));
        assertTrue(allStringsMatcher.isMatch("12345"));
    }

    @Test
    public void testPatternExactMatch() {
        Rule.RPattern exactMatchPattern = Rule.pattern("^exact$");

        // Test exact match
        assertTrue(exactMatchPattern.isMatch("exact"));
        assertTrue(!exactMatchPattern.isMatch("exactly"));
        assertTrue(!exactMatchPattern.isMatch("inexact"));
    }

    @Test
    public void testPatternStartsWith() {
        Rule.RPattern startsWithPattern = Rule.pattern("^start");

        // Test starts with match
        assertTrue(startsWithPattern.isMatch("start here"));
        assertTrue(!startsWithPattern.isMatch("begin start"));
    }

    @Test
    public void testPatternEndsWith() {
        Rule.RPattern endsWithPattern = Rule.pattern("end$");

        // Test ends with match
        assertTrue(endsWithPattern.isMatch("this is the end"));
        assertTrue(!endsWithPattern.isMatch("endings are here"));
    }

    @Test
    public void testPatternContainsBox() {
        Rule.RPattern containsBoxPattern = Rule.pattern("^[abc]");

        // Test contains box match
        assertTrue(containsBoxPattern.isMatch("a"));
        assertTrue(containsBoxPattern.isMatch("b"));
        assertTrue(containsBoxPattern.isMatch("c"));
        assertTrue(!containsBoxPattern.isMatch("d"));
    }

    @Test
    public void testPatternComputeIfAbsent() {
        // Create a map and use computeIfAbsent to test the lambda expression
        Map<String, List<Rule>> lines = new HashMap<>();
        String patternKey = "a";
        List<Rule> rules = lines.computeIfAbsent(patternKey, k -> new ArrayList<>());

        // Verify that the list was created and added to the map
        assertTrue(lines.containsKey(patternKey));
        assertEquals(rules, lines.get(patternKey));
    }
}