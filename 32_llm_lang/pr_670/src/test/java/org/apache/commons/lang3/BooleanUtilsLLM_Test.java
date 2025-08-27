package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class BooleanUtilsLLM_Test {

    /**
     * Test for the corrected javadoc example in the toBooleanObject method.
     */
    @Test
    public void test_toBooleanObject_javadocExample() {
        assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject(Integer.valueOf(0)));
        assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject(Integer.valueOf(1)));
        assertEquals(null, BooleanUtils.toBooleanObject((Integer) null));
    }
}