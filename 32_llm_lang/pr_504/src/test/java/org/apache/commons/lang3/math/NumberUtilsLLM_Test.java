package org.apache.commons.lang3.math;

import static org.junit.jupiter.api.Assertions.assertThrows;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

public class NumberUtilsLLM_Test {

    @Test
    public void testCreateBigDecimalWithDoubleDash() {
        assertThrows(NumberFormatException.class, () -> NumberUtils.createBigDecimal("--123.45"), "createBigDecimal(\"--123.45\") should have failed.");
        assertThrows(NumberFormatException.class, () -> NumberUtils.createBigDecimal("--0"), "createBigDecimal(\"--0\") should have failed.");
    }
}