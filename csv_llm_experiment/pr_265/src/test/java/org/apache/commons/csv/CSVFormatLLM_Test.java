package org.apache.commons.csv;

import static org.apache.commons.csv.CSVFormat.RFC4180;
import static org.apache.commons.csv.Constants.CR;
import static org.apache.commons.csv.Constants.CRLF;
import static org.apache.commons.csv.Constants.LF;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CSVFormatLLM_Test {

    @Test
    public void testPostgreSQLCsvFormat() {
        CSVFormat format = CSVFormat.POSTGRESQL_CSV;
        assertEquals(',', format.getDelimiter());
        assertNull(format.getEscapeCharacter());
        assertTrue(format.getIgnoreEmptyLines());
        assertEquals(Character.valueOf('"'), format.getQuoteCharacter());
        assertEquals(LF, format.getRecordSeparator());
        assertEquals("", format.getNullString());
        assertEquals(QuoteMode.ALL_NON_NULL, format.getQuoteMode());
    }

    @Test
    public void testPostgreSQLTextFormat() {
        CSVFormat format = CSVFormat.POSTGRESQL_TEXT;
        assertEquals('\t', format.getDelimiter());
        assertEquals(Character.valueOf('\\'), format.getEscapeCharacter());
        assertTrue(format.getIgnoreEmptyLines());
        assertNull(format.getQuoteCharacter());
        assertEquals(LF, format.getRecordSeparator());
        assertEquals("\\N", format.getNullString());
        assertEquals(QuoteMode.ALL_NON_NULL, format.getQuoteMode());
    }

    @Test
    public void testPostgreSQLCsvFormatEscapeCharacter() {
        CSVFormat format = CSVFormat.POSTGRESQL_CSV;
        assertNull(format.getEscapeCharacter());
    }

    @Test
    public void testPostgreSQLTextFormatQuoteCharacter() {
        CSVFormat format = CSVFormat.POSTGRESQL_TEXT;
        assertNull(format.getQuoteCharacter());
    }

    @Test
    public void testInvalidEscapeCharacter() {
        assertThrows(IllegalArgumentException.class, () -> CSVFormat.DEFAULT.builder().setEscape(null).build());
    }

    @Test
    public void testInvalidQuoteCharacter() {
        assertThrows(IllegalArgumentException.class, () -> CSVFormat.DEFAULT.builder().setQuote(null).build());
    }
}