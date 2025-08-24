package org.apache.commons.csv;

import static org.apache.commons.csv.CSVFormat.RFC4180;
import static org.apache.commons.csv.Constants.CR;
import static org.apache.commons.csv.Constants.CRLF;
import static org.apache.commons.csv.Constants.LF;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class CSVFormatLLM_Test {

    @Test
    public void testWithHeaderStringArray() {
        // Test withHeader method with a String array
        final String[] header = {"col1", "col2", "col3"};
        final CSVFormat format = CSVFormat.DEFAULT.withHeader(header);
        assertEquals(header.length, format.getHeader().length);
        assertEquals("col1", format.getHeader()[0]);
        assertEquals("col2", format.getHeader()[1]);
        assertEquals("col3", format.getHeader()[2]);
    }

    @Test
    public void testWithHeaderStringArrayNull() {
        // Test withHeader method with a null String array
        final CSVFormat format = CSVFormat.DEFAULT.withHeader((String[]) null);
        assertEquals(null, format.getHeader());
    }

    @Test
    public void testWithHeaderStringArrayEmpty() {
        // Test withHeader method with an empty String array
        final String[] header = {};
        final CSVFormat format = CSVFormat.DEFAULT.withHeader(header);
        assertEquals(header.length, format.getHeader().length);
    }

    @Test
    public void testWithHeaderStringArrayDuplicate() {
        // Test withHeader method with duplicate header names
        final String[] header = {"col1", "col1"};
        assertThrows(IllegalArgumentException.class, () -> CSVFormat.DEFAULT.withHeader(header));
    }
}