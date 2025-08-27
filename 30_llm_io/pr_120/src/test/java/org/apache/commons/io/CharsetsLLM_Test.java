package org.apache.commons.io;

import org.junit.jupiter.api.Test;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.SortedMap;
import static org.junit.jupiter.api.Assertions.*;

public class CharsetsLLM_Test {

    @Test
    public void testRequiredCharsets() {
        SortedMap<String, Charset> charsets = Charsets.requiredCharsets();
        assertNotNull(charsets);
        assertEquals(6, charsets.size());
        assertTrue(charsets.containsKey(StandardCharsets.ISO_8859_1.name()));
        assertTrue(charsets.containsKey(StandardCharsets.US_ASCII.name()));
        assertTrue(charsets.containsKey(StandardCharsets.UTF_16.name()));
        assertTrue(charsets.containsKey(StandardCharsets.UTF_16BE.name()));
        assertTrue(charsets.containsKey(StandardCharsets.UTF_16LE.name()));
        assertTrue(charsets.containsKey(StandardCharsets.UTF_8.name()));
    }

    @Test
    public void testToCharsetWithNull() {
        Charset charset = Charsets.toCharset((Charset) null);
        assertNotNull(charset);
        assertEquals(Charset.defaultCharset(), charset);
    }

    @Test
    public void testToCharsetWithCharset() {
        Charset charset = Charsets.toCharset(StandardCharsets.UTF_8);
        assertNotNull(charset);
        assertEquals(StandardCharsets.UTF_8, charset);
    }

    @Test
    public void testToCharsetWithStringNull() {
        Charset charset = Charsets.toCharset((String) null);
        assertNotNull(charset);
        assertEquals(Charset.defaultCharset(), charset);
    }

    @Test
    public void testToCharsetWithString() {
        Charset charset = Charsets.toCharset(StandardCharsets.UTF_8.name());
        assertNotNull(charset);
        assertEquals(StandardCharsets.UTF_8, charset);
    }
}