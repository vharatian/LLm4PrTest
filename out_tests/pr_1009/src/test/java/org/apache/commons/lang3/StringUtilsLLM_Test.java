package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class StringUtilsLLM_Test {

    @Test
    public void testIndexOfIgnoreCase_Space() {
        assertEquals(0, StringUtils.indexOfIgnoreCase(" ", " "));
    }
}