package org.apache.commons.lang3.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class FastDatePrinterLLM_Test {

    /**
     * Test to ensure the UnpaddedNumberField constructor works as expected.
     * This test is added to cover the typo fix in the Javadoc comment.
     */
    @Test
    public void testUnpaddedNumberFieldConstructor() {
        // Create an instance of UnpaddedNumberField with a specific field
        FastDatePrinter.UnpaddedNumberField field = new FastDatePrinter.UnpaddedNumberField(Calendar.YEAR);

        // Verify the field is correctly set
        assertEquals(Calendar.YEAR, field.mField);
    }
}