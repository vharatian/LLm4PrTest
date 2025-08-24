package org.apache.commons.csv;

import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.IOException;
import java.io.StringReader;
import java.io.UncheckedIOException;
import org.junit.jupiter.api.Test;

public class CSVParserLLM_Test {

    @Test
    public void testUncheckedIOExceptionMessage() {
        final String csvData = "a,b,c\n1,2,3\nx,y,z";
        try (CSVParser parser = CSVParser.parse(new StringReader(csvData), CSVFormat.DEFAULT)) {
            // Simulate an IOException during parsing
            assertThrows(UncheckedIOException.class, () -> {
                throw new IOException("Simulated IOException");
            }, "Exception reading next record: Simulated IOException");
        } catch (IOException e) {
            // Handle the IOException thrown by CSVParser.parse
            e.printStackTrace();
        }
    }
}