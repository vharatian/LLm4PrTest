package org.apache.commons.csv;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import java.io.IOException;
import java.io.StringReader;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class CSVRecordLLM_Test {

    @Test
    public void testDuplicateHeaderGet() throws IOException {
        final String csv = "A,A,B,B\n1,2,5,6\n";
        final CSVFormat format = CSVFormat.DEFAULT.builder().setHeader().build();
        try (final CSVParser parser = CSVParser.parse(csv, format)) {
            final CSVRecord record = parser.nextRecord();
            assertAll("Test that it gets the last instance of a column when there are duplicate headings",
                () -> assertEquals("2", record.get("A")),
                () -> assertEquals("6", record.get("B"))
            );
        }
    }

    @Test
    public void testDuplicateHeaderToMap() throws IOException {
        final String csv = "A,A,B,B\n1,2,5,6\n";
        final CSVFormat format = CSVFormat.DEFAULT.builder().setHeader().build();
        try (final CSVParser parser = CSVParser.parse(csv, format)) {
            final CSVRecord record = parser.nextRecord();
            final Map<String, String> map = record.toMap();
            assertAll("Test that it gets the last instance of a column when there are duplicate headings",
                () -> assertEquals("2", map.get("A")),
                () -> assertEquals("6", map.get("B"))
            );
        }
    }
}