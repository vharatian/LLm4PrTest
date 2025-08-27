package org.apache.commons.csv;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CSVRecordLLM_Test {

    private String[] values;
    private CSVRecord record;

    @BeforeEach
    public void setUp() throws Exception {
        values = new String[] { "A", "B", "C" };
        final String rowData = String.join(",", values);
        try (final CSVParser parser = CSVFormat.DEFAULT.parse(new StringReader(rowData))) {
            record = parser.iterator().next();
        }
    }

    @Test
    public void testIsSetInt() {
        // Test with valid indices
        assertTrue(record.isSet(0));
        assertTrue(record.isSet(1));
        assertTrue(record.isSet(2));

        // Test with invalid indices
        assertFalse(record.isSet(-1));
        assertFalse(record.isSet(3));
    }
}