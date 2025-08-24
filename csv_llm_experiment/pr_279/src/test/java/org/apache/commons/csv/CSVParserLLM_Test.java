package org.apache.commons.csv;

import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.StringReader;
import org.junit.jupiter.api.Test;

public class CSVParserLLM_Test {

    @Test
    public void testDuplicateHeadersWithMissingColumnNames() {
        // Test case to ensure IllegalArgumentException is thrown when duplicate headers with missing column names are not allowed
        String csvData = "a,,a\n1,2,3\nx,y,z";
        assertThrows(IllegalArgumentException.class, () -> {
            CSVParser.parse(csvData, CSVFormat.DEFAULT.withHeader().withAllowMissingColumnNames(false));
        });
    }

    @Test
    public void testDuplicateHeadersWithAllowedMissingColumnNames() {
        // Test case to ensure no exception is thrown when duplicate headers with missing column names are allowed
        String csvData = "a,,a\n1,2,3\nx,y,z";
        try (CSVParser parser = CSVParser.parse(csvData, CSVFormat.DEFAULT.withHeader().withAllowMissingColumnNames(true))) {
            parser.getRecords();
        } catch (Exception e) {
            throw new RuntimeException("Test failed: Exception should not be thrown when missing column names are allowed", e);
        }
    }
}