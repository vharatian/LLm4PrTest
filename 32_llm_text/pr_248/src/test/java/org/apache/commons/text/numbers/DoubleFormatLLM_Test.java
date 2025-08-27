package org.apache.commons.text.numbers;

import org.junit.jupiter.api.Test;

import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.function.DoubleFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoubleFormatLLM_Test {

    @Test
    public void testPlainFormat() {
        DoubleFunction<String> formatter = DoubleFormat.PLAIN.builder().build();
        assertEquals("0.0", formatter.apply(0.0));
        assertEquals("12.401", formatter.apply(12.401));
        assertEquals("100000.0", formatter.apply(100000.0));
        assertEquals("1450000000.0", formatter.apply(1450000000.0));
        assertEquals("0.0000000000123", formatter.apply(0.0000000000123));
    }

    @Test
    public void testScientificFormat() {
        DoubleFunction<String> formatter = DoubleFormat.SCIENTIFIC.builder().build();
        assertEquals("0.0", formatter.apply(0.0));
        assertEquals("1.2401E1", formatter.apply(12.401));
        assertEquals("1.0E5", formatter.apply(100000.0));
        assertEquals("1.45E9", formatter.apply(1450000000.0));
        assertEquals("1.23E-11", formatter.apply(0.0000000000123));
    }

    @Test
    public void testEngineeringFormat() {
        DoubleFunction<String> formatter = DoubleFormat.ENGINEERING.builder().build();
        assertEquals("0.0", formatter.apply(0.0));
        assertEquals("12.401", formatter.apply(12.401));
        assertEquals("100.0E3", formatter.apply(100000.0));
        assertEquals("1.45E9", formatter.apply(1450000000.0));
        assertEquals("12.3E-12", formatter.apply(0.0000000000123));
    }

    @Test
    public void testMixedFormat() {
        DoubleFunction<String> formatter = DoubleFormat.MIXED.builder().build();
        assertEquals("0.0", formatter.apply(0.0));
        assertEquals("12.401", formatter.apply(12.401));
        assertEquals("100000.0", formatter.apply(100000.0));
        assertEquals("1.45E9", formatter.apply(1450000000.0));
        assertEquals("1.23E-11", formatter.apply(0.0000000000123));
    }

    @Test
    public void testCustomFormatSymbols() {
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance(Locale.FRANCE);
        DoubleFunction<String> formatter = DoubleFormat.PLAIN.builder()
                .formatSymbols(symbols)
                .build();
        assertEquals("0,0", formatter.apply(0.0));
        assertEquals("12,401", formatter.apply(12.401));
        assertEquals("100000,0", formatter.apply(100000.0));
        assertEquals("1450000000,0", formatter.apply(1450000000.0));
        assertEquals("0,0000000000123", formatter.apply(0.0000000000123));
    }

    @Test
    public void testMaxPrecision() {
        DoubleFunction<String> formatter = DoubleFormat.PLAIN.builder()
                .maxPrecision(4)
                .build();
        assertEquals("12.40", formatter.apply(12.401));
        assertEquals("100000.0", formatter.apply(100000.0));
    }

    @Test
    public void testMinDecimalExponent() {
        DoubleFunction<String> formatter = DoubleFormat.PLAIN.builder()
                .minDecimalExponent(-2)
                .build();
        assertEquals("3.14", formatter.apply(3.14159));
        assertEquals("0.0", formatter.apply(0.001));
    }

    @Test
    public void testPlainFormatMaxDecimalExponent() {
        DoubleFunction<String> formatter = DoubleFormat.MIXED.builder()
                .plainFormatMaxDecimalExponent(2)
                .build();
        assertEquals("999.0", formatter.apply(999));
        assertEquals("1.0E3", formatter.apply(1000));
    }

    @Test
    public void testPlainFormatMinDecimalExponent() {
        DoubleFunction<String> formatter = DoubleFormat.MIXED.builder()
                .plainFormatMinDecimalExponent(-2)
                .build();
        assertEquals("0.01", formatter.apply(0.01));
        assertEquals("9.9E-3", formatter.apply(0.0099));
    }

    @Test
    public void testAllowSignedZero() {
        DoubleFunction<String> formatter = DoubleFormat.PLAIN.builder()
                .allowSignedZero(true)
                .build();
        assertEquals("-0.0", formatter.apply(-0.0));
    }

    @Test
    public void testIncludeFractionPlaceholder() {
        DoubleFunction<String> formatter = DoubleFormat.PLAIN.builder()
                .includeFractionPlaceholder(true)
                .build();
        assertEquals("1.0", formatter.apply(1));
    }

    @Test
    public void testMinusSign() {
        DoubleFunction<String> formatter = DoubleFormat.PLAIN.builder()
                .minusSign('−')
                .build();
        assertEquals("−12.401", formatter.apply(-12.401));
    }

    @Test
    public void testDecimalSeparator() {
        DoubleFunction<String> formatter = DoubleFormat.PLAIN.builder()
                .decimalSeparator(',')
                .build();
        assertEquals("12,401", formatter.apply(12.401));
    }

    @Test
    public void testGroupingSeparator() {
        DoubleFunction<String> formatter = DoubleFormat.PLAIN.builder()
                .groupingSeparator(' ')
                .groupThousands(true)
                .build();
        assertEquals("1 000", formatter.apply(1000));
    }

    @Test
    public void testExponentSeparator() {
        DoubleFunction<String> formatter = DoubleFormat.SCIENTIFIC.builder()
                .exponentSeparator("e")
                .build();
        assertEquals("1.0e5", formatter.apply(100000.0));
    }

    @Test
    public void testAlwaysIncludeExponent() {
        DoubleFunction<String> formatter = DoubleFormat.SCIENTIFIC.builder()
                .alwaysIncludeExponent(true)
                .build();
        assertEquals("1.0E0", formatter.apply(1.0));
    }

    @Test
    public void testInfinity() {
        DoubleFunction<String> formatter = DoubleFormat.PLAIN.builder()
                .infinity("Inf")
                .build();
        assertEquals("Inf", formatter.apply(Double.POSITIVE_INFINITY));
        assertEquals("-Inf", formatter.apply(Double.NEGATIVE_INFINITY));
    }

    @Test
    public void testNaN() {
        DoubleFunction<String> formatter = DoubleFormat.PLAIN.builder()
                .nan("NotANumber")
                .build();
        assertEquals("NotANumber", formatter.apply(Double.NaN));
    }
}