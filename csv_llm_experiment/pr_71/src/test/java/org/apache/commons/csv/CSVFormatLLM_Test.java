package org.apache.commons.csv;

import static org.apache.commons.csv.CSVFormat.RFC4180;
import static org.apache.commons.csv.Constants.CR;
import static org.apache.commons.csv.Constants.CRLF;
import static org.apache.commons.csv.Constants.LF;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.io.StringWriter;

import org.junit.jupiter.api.Test;

public class CSVFormatLLM_Test {

    @Test
    public void testFormatWithRecordSeparator() {
        CSVFormat format = CSVFormat.DEFAULT.withRecordSeparator(CRLF);
        assertEquals("a,b,c\r\n", format.format("a", "b", "c"));

        format = CSVFormat.DEFAULT.withRecordSeparator(LF);
        assertEquals("a,b,c\n", format.format("a", "b", "c"));

        format = CSVFormat.DEFAULT.withRecordSeparator(CR);
        assertEquals("a,b,c\r", format.format("a", "b", "c"));
    }

    @Test
    public void testFormatWithoutRecordSeparator() {
        CSVFormat format = CSVFormat.DEFAULT.withRecordSeparator(null);
        assertEquals("a,b,c", format.format("a", "b", "c"));
    }

    @Test
    public void testFormatWithEmptyRecordSeparator() {
        CSVFormat format = CSVFormat.DEFAULT.withRecordSeparator("");
        assertEquals("a,b,c", format.format("a", "b", "c"));
    }

    @Test
    public void testFormatWithCustomRecordSeparator() {
        CSVFormat format = CSVFormat.DEFAULT.withRecordSeparator(";");
        assertEquals("a,b,c;", format.format("a", "b", "c"));
    }

    @Test
    public void testFormatWithNullValues() {
        CSVFormat format = CSVFormat.DEFAULT.withRecordSeparator(CRLF);
        assertEquals(",\r\n", format.format(null, null));
    }

    @Test
    public void testFormatWithIOException() {
        CSVFormat format = CSVFormat.DEFAULT.withRecordSeparator(CRLF);
        assertThrows(IllegalStateException.class, () -> {
            try (StringWriter out = new StringWriter()) {
                out.close();
                format.format(out, "a", "b", "c");
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        });
    }
}