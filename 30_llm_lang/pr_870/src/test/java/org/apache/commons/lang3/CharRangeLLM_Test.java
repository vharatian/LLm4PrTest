package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class CharRangeLLM_Test {

    @Test
    public void testContainsNullArg() {
        final CharRange range = CharRange.is('a');
        final NullPointerException e = assertThrows(NullPointerException.class, () -> range.contains(null));
        assertEquals("range", e.getMessage());
    }
}