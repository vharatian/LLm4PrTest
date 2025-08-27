package org.apache.commons.csv;

import static org.apache.commons.csv.CSVFormat.RFC4180;
import static org.apache.commons.csv.Constants.CR;
import static org.apache.commons.csv.Constants.CRLF;
import static org.apache.commons.csv.Constants.LF;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class CSVFormatLLM_Test {

    /**
     * Test to ensure that the default settings are correctly documented.
     */
    @Test
    public void testDefaultSettingsDocumentation() {
        CSVFormat defaultFormat = CSVFormat.DEFAULT;
        assertEquals(',', defaultFormat.getDelimiter());
        assertEquals(Character.valueOf('"'), defaultFormat.getQuoteCharacter());
        assertEquals("\r\n", defaultFormat.getRecordSeparator());
        assertEquals(false, defaultFormat.getIgnoreEmptyLines());
        assertEquals(true, defaultFormat.getAllowMissingColumnNames());
        assertEquals(true, defaultFormat.getAllowDuplicateHeaderNames());
    }

    /**
     * Test to ensure that the delimiter cannot be a line break.
     */
    @Test
    public void testDelimiterCannotBeLineBreak() {
        assertThrows(IllegalArgumentException.class, () -> CSVFormat.newFormat(CR));
        assertThrows(IllegalArgumentException.class, () -> CSVFormat.newFormat(LF));
    }

    /**
     * Test to ensure that the quote character cannot be a line break.
     */
    @Test
    public void testQuoteCharCannotBeLineBreak() {
        assertThrows(IllegalArgumentException.class, () -> CSVFormat.DEFAULT.withQuote(CR));
        assertThrows(IllegalArgumentException.class, () -> CSVFormat.DEFAULT.withQuote(LF));
    }

    /**
     * Test to ensure that the escape character cannot be a line break.
     */
    @Test
    public void testEscapeCharCannotBeLineBreak() {
        assertThrows(IllegalArgumentException.class, () -> CSVFormat.DEFAULT.withEscape(CR));
        assertThrows(IllegalArgumentException.class, () -> CSVFormat.DEFAULT.withEscape(LF));
    }

    /**
     * Test to ensure that the comment marker cannot be a line break.
     */
    @Test
    public void testCommentMarkerCannotBeLineBreak() {
        assertThrows(IllegalArgumentException.class, () -> CSVFormat.DEFAULT.withCommentMarker(CR));
        assertThrows(IllegalArgumentException.class, () -> CSVFormat.DEFAULT.withCommentMarker(LF));
    }
}