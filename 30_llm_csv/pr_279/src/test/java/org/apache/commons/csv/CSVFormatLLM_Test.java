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
     * Test for the updated Javadoc comment in setAllowMissingColumnNames method.
     */
    @Test
    public void testSetAllowMissingColumnNames() {
        CSVFormat.Builder builder = CSVFormat.DEFAULT.builder();
        CSVFormat format = builder.setAllowMissingColumnNames(true).build();
        assertEquals(true, format.getAllowMissingColumnNames());

        format = builder.setAllowMissingColumnNames(false).build();
        assertEquals(false, format.getAllowMissingColumnNames());
    }

    /**
     * Test for the updated Javadoc comment in setIgnoreHeaderCase method.
     */
    @Test
    public void testSetIgnoreHeaderCase() {
        CSVFormat.Builder builder = CSVFormat.DEFAULT.builder();
        CSVFormat format = builder.setIgnoreHeaderCase(true).build();
        assertEquals(true, format.getIgnoreHeaderCase());

        format = builder.setIgnoreHeaderCase(false).build();
        assertEquals(false, format.getIgnoreHeaderCase());
    }

    /**
     * Test for the updated Javadoc comment in getIgnoreHeaderCase method.
     */
    @Test
    public void testGetIgnoreHeaderCase() {
        CSVFormat format = CSVFormat.DEFAULT.builder().setIgnoreHeaderCase(true).build();
        assertEquals(true, format.getIgnoreHeaderCase());

        format = CSVFormat.DEFAULT.builder().setIgnoreHeaderCase(false).build();
        assertEquals(false, format.getIgnoreHeaderCase());
    }
}