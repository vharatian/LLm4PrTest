package org.apache.commons.text.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DoubleFormatLLM_Test {

    /**
     * Test to ensure that the formatted output string created by DoubleFormat
     * matches the precision required to exactly represent the input double.
     */
    @Test
    void testFormattedOutputPrecision() {
        DoubleFunction<String> fmt = DoubleFormat.PLAIN.builder().build();
        double value = 12345.6789;
        String formatted = fmt.apply(value);
        double parsed = Double.parseDouble(formatted);
        Assertions.assertEquals(value, parsed, "Formatted double string did not match input value");
    }

    /**
     * Test to ensure that the formatted output string created by DoubleFormat
     * cannot extend beyond the precision provided by Double.toString().
     */
    @Test
    void testFormattedOutputPrecisionLimit() {
        DoubleFunction<String> fmt = DoubleFormat.PLAIN.builder().build();
        double value = 1.2345678901234567e+30;
        String formatted = fmt.apply(value);
        double parsed = Double.parseDouble(formatted);
        Assertions.assertEquals(value, parsed, "Formatted double string did not match input value");
    }

    /**
     * Test to ensure that the formatted output string created by DoubleFormat
     * matches the precision required to exactly represent the input double for negative values.
     */
    @Test
    void testFormattedOutputPrecisionNegative() {
        DoubleFunction<String> fmt = DoubleFormat.PLAIN.builder().build();
        double value = -12345.6789;
        String formatted = fmt.apply(value);
        double parsed = Double.parseDouble(formatted);
        Assertions.assertEquals(value, parsed, "Formatted double string did not match input value");
    }

    /**
     * Test to ensure that the formatted output string created by DoubleFormat
     * matches the precision required to exactly represent the input double for very small values.
     */
    @Test
    void testFormattedOutputPrecisionSmallValue() {
        DoubleFunction<String> fmt = DoubleFormat.PLAIN.builder().build();
        double value = 1.2345678901234567e-30;
        String formatted = fmt.apply(value);
        double parsed = Double.parseDouble(formatted);
        Assertions.assertEquals(value, parsed, "Formatted double string did not match input value");
    }

    /**
     * Test to ensure that the formatted output string created by DoubleFormat
     * matches the precision required to exactly represent the input double for very large values.
     */
    @Test
    void testFormattedOutputPrecisionLargeValue() {
        DoubleFunction<String> fmt = DoubleFormat.PLAIN.builder().build();
        double value = 1.2345678901234567e+308;
        String formatted = fmt.apply(value);
        double parsed = Double.parseDouble(formatted);
        Assertions.assertEquals(value, parsed, "Formatted double string did not match input value");
    }
}