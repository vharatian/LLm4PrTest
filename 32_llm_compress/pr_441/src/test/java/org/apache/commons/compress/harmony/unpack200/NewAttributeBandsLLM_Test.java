package org.apache.commons.compress.harmony.unpack200;

import org.apache.commons.compress.utils.ParsingUtils;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.StringReader;
import static org.junit.jupiter.api.Assertions.*;

public class NewAttributeBandsLLM_Test {

    @Test
    public void testReadNumber() throws IOException {
        NewAttributeBands newAttributeBands = new NewAttributeBands(null, null);

        StringReader positiveNumberStream = new StringReader("123");
        StringReader negativeNumberStream = new StringReader("-456");
        StringReader invalidNumberStream = new StringReader("abc");

        assertEquals(123, newAttributeBands.readNumber(positiveNumberStream));
        assertEquals(-456, newAttributeBands.readNumber(negativeNumberStream));
        assertNull(newAttributeBands.readNumber(invalidNumberStream));
    }

    @Test
    public void testReadNumberWithParsingUtils() throws IOException {
        NewAttributeBands newAttributeBands = new NewAttributeBands(null, null);

        StringReader positiveNumberStream = new StringReader("789");
        StringReader negativeNumberStream = new StringReader("-1011");

        assertEquals(789, newAttributeBands.readNumber(positiveNumberStream));
        assertEquals(-1011, newAttributeBands.readNumber(negativeNumberStream));
    }
}