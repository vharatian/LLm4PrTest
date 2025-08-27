package org.apache.commons.codec.language.bm;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PhoneticEngineLLM_Test {

    private static final Integer TEN = Integer.valueOf(10);

    public static Stream<Arguments> data() {
        return Stream.of(
            // Test case to specifically check the change from input.substring(0, 2).equals("d'") to input.startsWith("d'")
            Arguments.of("d'ortley", "(ortlaj|ortlej)-(dortlaj|dortlej)", NameType.GENERIC, RuleType.EXACT, Boolean.TRUE, TEN),
            // Additional test cases to ensure no regression in functionality
            Arguments.of("d'angelo", "(anxelo|anxelo|danxelo|danxelo)", NameType.GENERIC, RuleType.EXACT, Boolean.TRUE, TEN),
            Arguments.of("d'angelo", "(anxelo|anxelo|danxelo|danxelo)", NameType.GENERIC, RuleType.APPROX, Boolean.TRUE, TEN)
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