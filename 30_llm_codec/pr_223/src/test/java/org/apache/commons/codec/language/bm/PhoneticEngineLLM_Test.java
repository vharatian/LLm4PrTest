package org.apache.commons.codec.language.bm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PhoneticEngineLLM_Test {

    private static final Integer TEN = Integer.valueOf(10);

    // Provide test data for the new test cases
    public static Stream<Arguments> data() {
        return Stream.of(
            // Test case to check the behavior of split with limit -1
            Arguments.of("O'Connor", "okonor", NameType.SEPHARDIC, RuleType.APPROX, Boolean.TRUE, TEN),
            // Test case to check the behavior when words2 is empty
            Arguments.of("van", "", NameType.GENERIC, RuleType.APPROX, Boolean.FALSE, TEN)
        );
    }

    @ParameterizedTest
    @MethodSource("data")
    public void testEncode(final String name, final String phoneticExpected, final NameType nameType,
                           final RuleType ruleType, final boolean concat, final int maxPhonemes) {
        final PhoneticEngine engine = new PhoneticEngine(nameType, ruleType, concat, maxPhonemes);
        final String phoneticActual = engine.encode(name);
        assertEquals(phoneticExpected, phoneticActual, "phoneme incorrect");
        if (concat) {
            final String[] split = phoneticActual.split("\\|");
            assertTrue(split.length <= maxPhonemes);
        } else {
            final String[] words = phoneticActual.split("-");
            for (final String word : words) {
                final String[] split = word.split("\\|");
                assertTrue(split.length <= maxPhonemes);
            }
        }
    }
}