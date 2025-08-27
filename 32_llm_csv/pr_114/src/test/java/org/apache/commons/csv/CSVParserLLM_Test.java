package org.apache.commons.csv;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.StringReader;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

public class CSVParserLLM_Test {

    @Test
    public void testDuplicateHeadersModeAllowAll() throws Exception {
        // Test case for DuplicateHeaderMode.ALLOW_ALL
        final String csvData = "a,b,a\n1,2,3\nx,y,z";
        final CSVFormat format = CSVFormat.DEFAULT.withHeader().withDuplicateHeaderMode(DuplicateHeaderMode.ALLOW_ALL);
        try (final CSVParser parser = CSVParser.parse(new StringReader(csvData), format)) {
            final Iterator<CSVRecord> records = parser.iterator();
            while (records.hasNext()) {
                final CSVRecord record = records.next();
                // Validate that records are parsed correctly even with duplicate headers
                assertNotNull(record);
            }
        }
    }

    @Test
    public void testDuplicateHeadersModeAllowEmpty() throws Exception {
        // Test case for DuplicateHeaderMode.ALLOW_EMPTY
        final String csvData = "a,,a\n1,2,3\nx,y,z";
        final CSVFormat format = CSVFormat.DEFAULT.withHeader().withDuplicateHeaderMode(DuplicateHeaderMode.ALLOW_EMPTY);
        try (final CSVParser parser = CSVParser.parse(new StringReader(csvData), format)) {
            final Iterator<CSVRecord> records = parser.iterator();
            while (records.hasNext()) {
                final CSVRecord record = records.next();
                // Validate that records are parsed correctly even with empty duplicate headers
                assertNotNull(record);
            }
        }
    }

    @Test
    public void testDuplicateHeadersModeDisallow() {
        // Test case for DuplicateHeaderMode.DISALLOW
        final String csvData = "a,b,a\n1,2,3\nx,y,z";
        final CSVFormat format = CSVFormat.DEFAULT.withHeader().withDuplicateHeaderMode(DuplicateHeaderMode.DISALLOW);
        assertThrows(IllegalArgumentException.class, () -> {
            try (final CSVParser parser = CSVParser.parse(new StringReader(csvData), format)) {
                // This should throw an IllegalArgumentException due to duplicate headers
                parser.getRecords();
            }
        });
    }
}