package org.apache.commons.csv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.junit.Test;

public class CSVParserLLM_Test {

    @Test
    public void testAllowDuplicateHeaderNames() throws IOException {
        final String csvData = "a,a,a\n1,2,3\nx,y,z";
        final CSVFormat format = CSVFormat.DEFAULT.withHeader().withAllowDuplicateHeaderNames();
        try (final CSVParser parser = CSVParser.parse(csvData, format)) {
            final List<CSVRecord> records = parser.getRecords();
            assertEquals(2, records.size());
            assertEquals("1", records.get(0).get(0));
            assertEquals("2", records.get(0).get(1));
            assertEquals("3", records.get(0).get(2));
            assertEquals("x", records.get(1).get(0));
            assertEquals("y", records.get(1).get(1));
            assertEquals("z", records.get(1).get(2));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDisallowDuplicateHeaderNames() throws IOException {
        final String csvData = "a,a,a\n1,2,3\nx,y,z";
        final CSVFormat format = CSVFormat.DEFAULT.withHeader();
        try (final CSVParser parser = CSVParser.parse(csvData, format)) {
            fail("Expected IllegalArgumentException due to duplicate header names");
        }
    }

    @Test
    public void testAllowMissingColumnNames() throws IOException {
        final String csvData = "a,,c\n1,2,3\nx,y,z";
        final CSVFormat format = CSVFormat.DEFAULT.withHeader().withAllowMissingColumnNames();
        try (final CSVParser parser = CSVParser.parse(csvData, format)) {
            final List<CSVRecord> records = parser.getRecords();
            assertEquals(2, records.size());
            assertEquals("1", records.get(0).get(0));
            assertEquals("2", records.get(0).get(1));
            assertEquals("3", records.get(0).get(2));
            assertEquals("x", records.get(1).get(0));
            assertEquals("y", records.get(1).get(1));
            assertEquals("z", records.get(1).get(2));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDisallowMissingColumnNames() throws IOException {
        final String csvData = "a,,c\n1,2,3\nx,y,z";
        final CSVFormat format = CSVFormat.DEFAULT.withHeader();
        try (final CSVParser parser = CSVParser.parse(csvData, format)) {
            fail("Expected IllegalArgumentException due to missing column names");
        }
    }
}