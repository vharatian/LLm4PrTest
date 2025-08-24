package org.apache.commons.compress.harmony.pack200;

import org.apache.commons.compress.utils.ParsingUtils;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.StringReader;
import static org.junit.jupiter.api.Assertions.*;

public class NewAttributeBandsLLM_Test {

    @Test
    public void testReadNumber() throws IOException {
        NewAttributeBands newAttributeBands = new NewAttributeBands(1, null, null, null);

        // Test positive number
        StringReader reader = new StringReader("1234");
        Integer result = newAttributeBands.readNumber(reader);
        assertEquals(1234, result);

        // Test negative number
        reader = new StringReader("-5678");
        result = newAttributeBands.readNumber(reader);
        assertEquals(-5678, result);

        // Test zero
        reader = new StringReader("0");
        result = newAttributeBands.readNumber(reader);
        assertEquals(0, result);

        // Test invalid input
        reader = new StringReader("abc");
        result = newAttributeBands.readNumber(reader);
        assertNull(result);
    }
}