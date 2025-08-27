package org.apache.commons.csv;

import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.StringReader;
import org.junit.jupiter.api.Test;

public class CSVParserLLM_Test {

    @Test
    public void testEmptyHeaderNotAllowed() {
        assertThrows(IllegalArgumentException.class, () -> {
            CSVParser.parse("a,,c\n1,2,3\nx,y,z", CSVFormat.DEFAULT.withHeader().withAllowMissingColumnNames(false));
        });
    }

    @Test
    public void testDuplicateHeaderNotAllowedWithEmptyHeader() {
        assertThrows(IllegalArgumentException.class, () -> {
            CSVParser.parse("a,,a\n1,2,3\nx,y,z", CSVFormat.DEFAULT.withHeader().withAllowDuplicateHeaderNames(false).withAllowMissingColumnNames(true));
        });
    }

    @Test
    public void testDuplicateHeaderAllowedWithEmptyHeader() throws Exception {
        try (CSVParser parser = CSVParser.parse("a,,a\n1,2,3\nx,y,z", CSVFormat.DEFAULT.withHeader().withAllowDuplicateHeaderNames(true).withAllowMissingColumnNames(true))) {
            // Ensure the parser does not throw an exception
            parser.getRecords();
        }
    }
}