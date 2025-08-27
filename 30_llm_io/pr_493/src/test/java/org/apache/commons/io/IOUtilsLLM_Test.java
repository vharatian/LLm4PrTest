package org.apache.commons.io;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.Test;

public class IOUtilsLLM_Test {

    /**
     * Tests the writeLines method with UTF-16 charset without BOM.
     */
    @Test
    public void testWriteLines_UTF16() throws IOException {
        Collection<String> lines = Arrays.asList("line1", "line2", "line3");
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Charset charset = StandardCharsets.UTF_16;

        assertDoesNotThrow(() -> IOUtils.writeLines(lines, null, output, charset));
        String result = output.toString(StandardCharsets.UTF_16BE.name());
        assertEquals("line1\nline2\nline3\n", result);
    }

    /**
     * Tests the writeLines method with UTF-16LE charset.
     */
    @Test
    public void testWriteLines_UTF16LE() throws IOException {
        Collection<String> lines = Arrays.asList("line1", "line2", "line3");
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Charset charset = StandardCharsets.UTF_16LE;

        assertDoesNotThrow(() -> IOUtils.writeLines(lines, null, output, charset));
        String result = output.toString(StandardCharsets.UTF_16LE.name());
        assertEquals("line1\nline2\nline3\n", result);
    }

    /**
     * Tests the writeLines method with a BOM for UTF-16.
     */
    @Test
    public void testWriteLines_UTF16_WithBOM() throws IOException {
        Collection<String> lines = Arrays.asList("line1", "line2", "line3");
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Charset charset = StandardCharsets.UTF_16;

        // Write BOM manually
        output.write(0xFE);
        output.write(0xFF);

        assertDoesNotThrow(() -> IOUtils.writeLines(lines, null, output, charset));
        String result = output.toString(StandardCharsets.UTF_16.name());
        assertEquals("\uFEFFline1\nline2\nline3\n", result);
    }

    /**
     * Tests the writeLines method with null charset.
     */
    @Test
    public void testWriteLines_NullCharset() throws IOException {
        Collection<String> lines = Arrays.asList("line1", "line2", "line3");
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        assertDoesNotThrow(() -> IOUtils.writeLines(lines, null, output, null));
        String result = output.toString();
        assertEquals("line1\nline2\nline3\n", result);
    }

    /**
     * Tests the writeLines method with null output stream.
     */
    @Test
    public void testWriteLines_NullOutputStream() {
        Collection<String> lines = Arrays.asList("line1", "line2", "line3");
        Charset charset = StandardCharsets.UTF_8;

        assertThrows(NullPointerException.class, () -> IOUtils.writeLines(lines, null, null, charset));
    }
}