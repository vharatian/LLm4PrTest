package org.apache.commons.csv;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;

public class CSVParserLLM_Test {

    @Test
    public void testHeaderComment() throws IOException {
        final String csvData = "# This is a header comment\na,b,c\n1,2,3\nx,y,z";
        final CSVFormat format = CSVFormat.DEFAULT.withCommentMarker('#').withHeader();
        try (final CSVParser parser = CSVParser.parse(csvData, format)) {
            assertTrue(parser.hasHeaderComment());
            assertEquals("This is a header comment", parser.getHeaderComment());
        }
    }

    @Test
    public void testNoHeaderComment() throws IOException {
        final String csvData = "a,b,c\n1,2,3\nx,y,z";
        final CSVFormat format = CSVFormat.DEFAULT.withHeader();
        try (final CSVParser parser = CSVParser.parse(csvData, format)) {
            assertFalse(parser.hasHeaderComment());
            assertNull(parser.getHeaderComment());
        }
    }

    @Test
    public void testTrailerComment() throws IOException {
        final String csvData = "a,b,c\n1,2,3\nx,y,z\n# This is a trailer comment";
        final CSVFormat format = CSVFormat.DEFAULT.withCommentMarker('#');
        try (final CSVParser parser = CSVParser.parse(csvData, format)) {
            List<CSVRecord> records = parser.getRecords();
            assertEquals(3, records.size());
            assertTrue(parser.hasTrailerComment());
            assertEquals("This is a trailer comment", parser.getTrailerComment());
        }
    }

    @Test
    public void testNoTrailerComment() throws IOException {
        final String csvData = "a,b,c\n1,2,3\nx,y,z";
        final CSVFormat format = CSVFormat.DEFAULT;
        try (final CSVParser parser = CSVParser.parse(csvData, format)) {
            List<CSVRecord> records = parser.getRecords();
            assertEquals(3, records.size());
            assertFalse(parser.hasTrailerComment());
            assertNull(parser.getTrailerComment());
        }
    }

    @Test
    public void testHeaderAndTrailerComments() throws IOException {
        final String csvData = "# Header comment\na,b,c\n1,2,3\nx,y,z\n# Trailer comment";
        final CSVFormat format = CSVFormat.DEFAULT.withCommentMarker('#').withHeader();
        try (final CSVParser parser = CSVParser.parse(csvData, format)) {
            assertTrue(parser.hasHeaderComment());
            assertEquals("Header comment", parser.getHeaderComment());
            List<CSVRecord> records = parser.getRecords();
            assertEquals(3, records.size());
            assertTrue(parser.hasTrailerComment());
            assertEquals("Trailer comment", parser.getTrailerComment());
        }
    }
}