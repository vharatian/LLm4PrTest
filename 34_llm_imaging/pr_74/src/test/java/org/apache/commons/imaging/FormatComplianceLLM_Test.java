package org.apache.commons.imaging;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FormatComplianceLLM_Test {

    @Test
    public void testCompareWithSingleValidValue() throws ImageReadException {
        FormatCompliance compliance = new FormatCompliance("test");
        assertTrue(compliance.compare("test", 5, 5));
    }

    @Test
    public void testCompareWithMultipleValidValues() throws ImageReadException {
        FormatCompliance compliance = new FormatCompliance("test");
        assertTrue(compliance.compare("test", new int[]{1, 2, 3, 4, 5}, 3));
    }

    @Test
    public void testCompareWithInvalidValue() {
        FormatCompliance compliance = new FormatCompliance("test");
        assertFalse(compliance.compare("test", new int[]{1, 2, 3, 4, 5}, 6));
    }

    @Test
    public void testCompareWithInvalidValueAndFailOnError() {
        FormatCompliance compliance = new FormatCompliance("test", true);
        assertThrows(ImageReadException.class, () -> {
            compliance.compare("test", new int[]{1, 2, 3, 4, 5}, 6);
        });
    }

    @Test
    public void testCompareWithInvalidValueMessage() {
        FormatCompliance compliance = new FormatCompliance("test");
        compliance.compare("test", new int[]{1, 2, 3, 4, 5}, 6);
        String expectedMessage = "test: Unexpected value: (valid: {1 (1), 2 (2), 3 (3), 4 (4), 5 (5)}, actual: 6 (6))";
        assertTrue(compliance.toString().contains(expectedMessage));
    }
}