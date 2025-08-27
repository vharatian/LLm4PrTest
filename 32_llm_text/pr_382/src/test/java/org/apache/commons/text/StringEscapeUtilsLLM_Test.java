package org.apache.commons.text;

import static org.apache.commons.text.StringEscapeUtils.escapeHtml3;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.StringWriter;

import org.junit.jupiter.api.Test;

public class StringEscapeUtilsLLM_Test {

    @Test
    public void testEscapeHtml3Updated() {
        // Test case to verify the updated HTML escape functionality
        final String input = "\"bread\" & \"butter\"";
        final String expected = "&quot;bread&quot; &amp; &quot;butter&quot;";
        assertEquals(expected, escapeHtml3(input));

        final StringWriter sw = new StringWriter();
        try {
            StringEscapeUtils.ESCAPE_HTML3.translate(input, sw);
        } catch (final IOException e) {
            // Handle exception
        }
        final String actual = sw.toString();
        assertEquals(expected, actual);
    }
}