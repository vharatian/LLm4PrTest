package org.apache.commons.csv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.StringWriter;
import java.io.IOException;

import org.junit.Test;

public class CSVFormatLLM_Test {

    @Test
    public void testFormatWithTryWithoutFinal() {
        CSVFormat format = CSVFormat.DEFAULT;
        StringWriter out = new StringWriter();
        try (CSVPrinter csvPrinter = new CSVPrinter(out, format)) {
            csvPrinter.printRecord("a", "b", "c");
            assertEquals("a,b,c", out.toString().trim());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    public void testWithAllowDuplicateHeaderNamesIndentation() {
        CSVFormat format = CSVFormat.DEFAULT.withAllowDuplicateHeaderNames();
        assertEquals(true, format.getAllowDuplicateHeaderNames());
    }

    @Test
    public void testWithAllowDuplicateHeaderNamesBooleanIndentation() {
        CSVFormat format = CSVFormat.DEFAULT.withAllowDuplicateHeaderNames(false);
        assertEquals(false, format.getAllowDuplicateHeaderNames());
    }
}