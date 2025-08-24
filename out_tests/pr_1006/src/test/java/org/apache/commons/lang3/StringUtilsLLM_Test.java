package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class StringUtilsLLM_Test {

    @Test
    public void testIsMixedCaseWithWhitespace() {
        assertFalse(StringUtils.isMixedCase(" "));
    }
}