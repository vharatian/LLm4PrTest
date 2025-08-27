package org.apache.commons.csv;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

public class CSVFormatLLM_Test {

    /**
     * Test for the getAllowDuplicateHeaderNames method.
     */
    @Test
    public void testGetAllowDuplicateHeaderNames() {
        CSVFormat format = CSVFormat.DEFAULT.withAllowDuplicateHeaderNames(true);
        assertTrue(format.getAllowDuplicateHeaderNames());

        format = CSVFormat.DEFAULT.withAllowDuplicateHeaderNames(false);
        assertFalse(format.getAllowDuplicateHeaderNames());
    }

    /**
     * Test for the withAllowDuplicateHeaderNames method.
     */
    @Test
    public void testWithAllowDuplicateHeaderNames() {
        CSVFormat format = CSVFormat.DEFAULT.withAllowDuplicateHeaderNames();
        assertTrue(format.getAllowDuplicateHeaderNames());

        format = CSVFormat.DEFAULT.withAllowDuplicateHeaderNames(false);
        CSVFormat newFormat = format.withAllowDuplicateHeaderNames();
        assertTrue(newFormat.getAllowDuplicateHeaderNames());
    }

    /**
     * Test for the withAllowDuplicateHeaderNames method with a parameter.
     */
    @Test
    public void testWithAllowDuplicateHeaderNamesWithParameter() {
        CSVFormat format = CSVFormat.DEFAULT.withAllowDuplicateHeaderNames(false);
        CSVFormat newFormat = format.withAllowDuplicateHeaderNames(true);
        assertTrue(newFormat.getAllowDuplicateHeaderNames());

        newFormat = format.withAllowDuplicateHeaderNames(false);
        assertFalse(newFormat.getAllowDuplicateHeaderNames());
    }
}