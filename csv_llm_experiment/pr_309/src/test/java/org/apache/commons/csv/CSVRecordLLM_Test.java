package org.apache.commons.csv;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.StringReader;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CSVRecordLLM_Test {

    private CSVRecord recordWithDuplicateHeaders;

    @BeforeEach
    public void setUp() throws Exception {
        final String rowData = "A,B,C";
        final String headerData = "first,second,first";
        try (final CSVParser parser = CSVFormat.DEFAULT.withHeader(headerData.split(",")).parse(new StringReader(rowData))) {
            recordWithDuplicateHeaders = parser.iterator().next();
        }
    }

    @Test
    public void testGetStringWithDuplicateHeaders() {
        // The last occurrence of the header "first" should be returned
        assertEquals("C", recordWithDuplicateHeaders.get("first"));
    }

    @Test
    public void testToMapWithDuplicateHeaders() {
        // The map should contain the last occurrence of the header "first"
        final Map<String, String> map = recordWithDuplicateHeaders.toMap();
        assertEquals("C", map.get("first"));
        assertEquals("B", map.get("second"));
    }

    @Test
    public void testGetStringWithInvalidHeader() {
        // Test with a header that does not exist
        assertThrows(IllegalArgumentException.class, () -> recordWithDuplicateHeaders.get("nonexistent"));
    }
}