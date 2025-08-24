package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

public class CharSequenceUtilsLLM_Test {

    @ParameterizedTest
    @MethodSource("lastIndexWithNegativeStart")
    public void testLastIndexOfWithNegativeStart(final CharSequence cs, final CharSequence search, final int start, final int expected) {
        assertEquals(expected, CharSequenceUtils.lastIndexOf(cs, search, start));
    }

    static Stream<Arguments> lastIndexWithNegativeStart() {
        return Stream.of(
            arguments("abc", "b", -1, -1),
            arguments(new StringBuilder("abc"), "b", -1, -1),
            arguments(new StringBuffer("abc"), "b", -1, -1),
            arguments("abc", new StringBuilder("b"), -1, -1),
            arguments(new StringBuilder("abc"), new StringBuilder("b"), -1, -1),
            arguments(new StringBuffer("abc"), new StringBuffer("b"), -1, -1),
            arguments(new StringBuilder("abc"), new StringBuffer("b"), -1, -1)
        );
    }

    @Test
    public void testLastIndexOfWithNegativeStartAndNullSearchChar() {
        assertThrows(NullPointerException.class, () -> CharSequenceUtils.lastIndexOf("abc", null, -1));
        assertThrows(NullPointerException.class, () -> CharSequenceUtils.lastIndexOf(new StringBuilder("abc"), null, -1));
        assertThrows(NullPointerException.class, () -> CharSequenceUtils.lastIndexOf(new StringBuffer("abc"), null, -1));
    }
}