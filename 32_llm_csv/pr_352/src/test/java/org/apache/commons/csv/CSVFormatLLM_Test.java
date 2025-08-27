package org.apache.commons.csv;

import static org.apache.commons.csv.Constants.CR;
import static org.apache.commons.csv.Constants.LF;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class CSVFormatLLM_Test {

    /**
     * Test to ensure that setting QuoteMode to NONE without setting an escape character
     * throws the correct IllegalArgumentException with the updated message.
     */
    @Test
    public void testQuotePolicyNoneWithoutEscapeThrowsExceptionWithUpdatedMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> 
            CSVFormat.newFormat('!').builder().setQuoteMode(QuoteMode.NONE).build()
        );
        assertEquals("Quote mode set to NONE but no escape character is set", exception.getMessage());
    }
}