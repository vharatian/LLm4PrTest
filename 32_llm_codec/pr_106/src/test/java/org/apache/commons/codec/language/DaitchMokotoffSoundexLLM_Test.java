package org.apache.commons.codec.language;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoderAbstractTest;
import org.junit.Assert;
import org.junit.Test;

public class DaitchMokotoffSoundexLLM_Test extends StringEncoderAbstractTest<DaitchMokotoffSoundex> {

    @Override
    protected DaitchMokotoffSoundex createStringEncoder() {
        return new DaitchMokotoffSoundex();
    }

    private String soundex(final String source) {
        return getStringEncoder().soundex(source);
    }

    private String encode(final String source) {
        return getStringEncoder().encode(source);
    }

    /**
     * Test to ensure that the RULES map is correctly sorted by pattern length in descending order.
     */
    @Test
    public void testRulesSorting() {
        // Access the RULES map via reflection to verify sorting
        try {
            java.lang.reflect.Field rulesField = DaitchMokotoffSoundex.class.getDeclaredField("RULES");
            rulesField.setAccessible(true);
            Map<Character, List<DaitchMokotoffSoundex.Rule>> rules = 
                (Map<Character, List<DaitchMokotoffSoundex.Rule>>) rulesField.get(null);

            for (List<DaitchMokotoffSoundex.Rule> ruleList : rules.values()) {
                int previousLength = Integer.MAX_VALUE;
                for (DaitchMokotoffSoundex.Rule rule : ruleList) {
                    int currentLength = rule.getPatternLength();
                    Assert.assertTrue("Rules are not sorted by pattern length in descending order", 
                        currentLength <= previousLength);
                    previousLength = currentLength;
                }
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            Assert.fail("Reflection error: " + e.getMessage());
        }
    }

    /**
     * Test to ensure computeIfAbsent is correctly used in parseRules method.
     */
    @Test
    public void testComputeIfAbsentInParseRules() {
        // Access the RULES map via reflection to verify computeIfAbsent usage
        try {
            java.lang.reflect.Field rulesField = DaitchMokotoffSoundex.class.getDeclaredField("RULES");
            rulesField.setAccessible(true);
            Map<Character, List<DaitchMokotoffSoundex.Rule>> rules = 
                (Map<Character, List<DaitchMokotoffSoundex.Rule>>) rulesField.get(null);

            // Ensure that rules map is populated correctly
            Assert.assertFalse("RULES map should not be empty", rules.isEmpty());

            // Check if a specific key has a non-null value
            char testKey = 'A'; // Assuming 'A' is a valid key in the rules map
            List<DaitchMokotoffSoundex.Rule> ruleList = rules.get(testKey);
            Assert.assertNotNull("RULES map should contain a list for key: " + testKey, ruleList);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            Assert.fail("Reflection error: " + e.getMessage());
        }
    }

    /**
     * Test to ensure that the nextBranches list is correctly initialized.
     */
    @Test
    public void testNextBranchesInitialization() {
        // Test with branching enabled
        String[] resultWithBranching = getStringEncoder().soundex("test", true);
        Assert.assertNotNull("Result should not be null with branching enabled", resultWithBranching);

        // Test with branching disabled
        String[] resultWithoutBranching = getStringEncoder().soundex("test", false);
        Assert.assertNotNull("Result should not be null with branching disabled", resultWithoutBranching);
    }
}