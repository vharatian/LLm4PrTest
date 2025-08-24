package org.apache.commons.lang3.math;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class IEEE754rUtilsLLM_Test {

    @Test
    public void testMinArrayNullPointerException() {
        assertThrows(
            NullPointerException.class,
            () -> IEEE754rUtils.min((double[]) null),
            "NullPointerException expected for null input"
        );
        assertThrows(
            NullPointerException.class,
            () -> IEEE754rUtils.min((float[]) null),
            "NullPointerException expected for null input"
        );
    }

    @Test
    public void testMaxArrayNullPointerException() {
        assertThrows(
            NullPointerException.class,
            () -> IEEE754rUtils.max((double[]) null),
            "NullPointerException expected for null input"
        );
        assertThrows(
            NullPointerException.class,
            () -> IEEE754rUtils.max((float[]) null),
            "NullPointerException expected for null input"
        );
    }
}