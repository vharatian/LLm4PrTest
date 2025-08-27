package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.StringWriter;

public class StringEscapeUtilsLLM_Test {

    @Test
    public void testUpdatedLinks() {
        // Test for the updated link in escapeEcmaScript method
        assertEquals("https://www.ietf.org/rfc/rfc4627.txt", "https://www.ietf.org/rfc/rfc4627.txt");

        // Test for the updated links in escapeHtml4 method
        assertEquals("https://web.archive.org/web/20060225074150/https://hotwired.lycos.com/webmonkey/reference/special_characters/", "https://web.archive.org/web/20060225074150/https://hotwired.lycos.com/webmonkey/reference/special_characters/");
        assertEquals("https://www.w3.org/TR/REC-html32#latin1", "https://www.w3.org/TR/REC-html32#latin1");
        assertEquals("https://www.w3.org/TR/REC-html40/sgml/entities.html", "https://www.w3.org/TR/REC-html40/sgml/entities.html");
        assertEquals("https://www.w3.org/TR/html401/charset.html#h-5.3", "https://www.w3.org/TR/html401/charset.html#h-5.3");
        assertEquals("https://www.w3.org/TR/html401/charset.html#code-position", "https://www.w3.org/TR/html401/charset.html#code-position");

        // Test for the updated links in escapeCsv method
        assertEquals("https://en.wikipedia.org/wiki/Comma-separated_values", "https://en.wikipedia.org/wiki/Comma-separated_values");
        assertEquals("https://datatracker.ietf.org/doc/html/rfc4180", "https://datatracker.ietf.org/doc/html/rfc4180");
    }
}