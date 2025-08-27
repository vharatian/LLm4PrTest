package org.apache.commons.lang3.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

public class NumberUtilsLLM_Test {

    @Test
    public void testToDoubleBigDecimal() {
        assertEquals(0.0d, NumberUtils.toDouble((BigDecimal) null), "toDouble(BigDecimal) 1 failed");
        assertEquals(8.5d, NumberUtils.toDouble(BigDecimal.valueOf(8.5d)), "toDouble(BigDecimal) 2 failed");
    }

    @Test
    public void testToDoubleBigDecimalWithDefault() {
        assertEquals(1.1d, NumberUtils.toDouble((BigDecimal) null, 1.1d), "toDouble(BigDecimal, double) 1 failed");
        assertEquals(8.5d, NumberUtils.toDouble(BigDecimal.valueOf(8.5d), 1.1d), "toDouble(BigDecimal, double) 2 failed");
    }
}