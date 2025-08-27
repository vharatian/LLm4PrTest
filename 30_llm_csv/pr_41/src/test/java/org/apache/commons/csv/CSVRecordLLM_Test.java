package org.apache.commons.csv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Map;
import java.util.TreeMap;
import org.junit.Before;
import org.junit.Test;

public class CSVRecordLLM_Test {

    private String[] values;
    private CSVRecord recordWithHeader;

    @Before
    public void setUp() throws Exception {
        values = new String[] { "A", "B", "C" };
        final String rowData = StringUtils.join(values, ',');
        final String[] headers = { "first", "second", "third" };
        try (final CSVParser parser = CSVFormat.DEFAULT.withHeader(headers).parse(new StringReader(rowData))) {
            recordWithHeader = parser.iterator().next();
        }
    }

    /**
     * Test to ensure that the toMap method correctly copies the record into a new Map.
     */
    @Test
    public void testToMap() {
        final Map<String, String> map = this.recordWithHeader.toMap();
        validateMap(map);
    }

    /**
     * Helper method to validate the contents of the map.
     */
    private void validateMap(final Map<String, String> map) {
        assertTrue(map.containsKey("first"));
        assertTrue(map.containsKey("second"));
        assertTrue(map.containsKey("third"));
        assertEquals("A", map.get("first"));
        assertEquals("B", map.get("second"));
        assertEquals("C", map.get("third"));
    }
}