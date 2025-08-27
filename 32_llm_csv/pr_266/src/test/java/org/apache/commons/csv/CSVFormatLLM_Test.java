package org.apache.commons.csv;

import static org.apache.commons.csv.Constants.CR;
import static org.apache.commons.csv.Constants.LF;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class CSVFormatLLM_Test {

    @Test
    public void testDelimiterCannotBeEmpty() {
        assertThrows(IllegalArgumentException.class, () -> CSVFormat.DEFAULT.builder().setDelimiter("").build());
    }
}