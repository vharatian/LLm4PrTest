package org.apache.commons.csv;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.StringReader;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

public class CSVParserLLM_Test {

    @Test
    public void testIteratorHasNextAfterClose() throws Exception {
        final String csvData = "a,b,c\n1,2,3\nx,y,z";
        final Iterator<CSVRecord> records;
        try (final CSVParser parser = CSVParser.parse(csvData, CSVFormat.DEFAULT)) {
            records = parser.iterator();
            assertTrue(records.hasNext());
        }
        assertFalse(records.hasNext());
    }

    @Test
    public void testIteratorNextAfterClose() throws Exception {
        final String csvData = "a,b,c\n1,2,3\nx,y,z";
        final Iterator<CSVRecord> records;
        try (final CSVParser parser = CSVParser.parse(csvData, CSVFormat.DEFAULT)) {
            records = parser.iterator();
            assertTrue(records.hasNext());
        }
        assertThrows(NoSuchElementException.class, records::next);
    }

    @Test
    public void testStreamAfterClose() throws Exception {
        final String csvData = "a,b,c\n1,2,3\nx,y,z";
        final Stream<CSVRecord> stream;
        try (final CSVParser parser = CSVParser.parse(csvData, CSVFormat.DEFAULT)) {
            stream = parser.stream();
            assertTrue(stream.iterator().hasNext());
        }
        assertFalse(stream.iterator().hasNext());
    }
}