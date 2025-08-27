package org.apache.commons.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class IOUtilsLLM_Test {

    @Test
    public void testWriteLines_UTF16BE() throws IOException {
        List<String> lines = Arrays.asList("line1", "line2", "line3");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        IOUtils.writeLines(lines, null, outputStream, StandardCharsets.UTF_16);
        byte[] expectedOutput = "line1\nline2\nline3\n".getBytes(StandardCharsets.UTF_16BE);
        assertTrue(Arrays.equals(expectedOutput, outputStream.toByteArray()));
    }

    @Test
    public void testWriteLines_UTF16LE() throws IOException {
        List<String> lines = Arrays.asList("line1", "line2", "line3");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        IOUtils.writeLines(lines, null, outputStream, StandardCharsets.UTF_16LE);
        byte[] expectedOutput = "line1\nline2\nline3\n".getBytes(StandardCharsets.UTF_16LE);
        assertTrue(Arrays.equals(expectedOutput, outputStream.toByteArray()));
    }

    @Test
    public void testWriteLines_UTF16WithBOM() throws IOException {
        List<String> lines = Arrays.asList("line1", "line2", "line3");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        // Write BOM manually
        outputStream.write(0xFE);
        outputStream.write(0xFF);
        IOUtils.writeLines(lines, null, outputStream, StandardCharsets.UTF_16);
        byte[] expectedOutput = new byte[]{(byte) 0xFE, (byte) 0xFF};
        expectedOutput = Arrays.copyOf(expectedOutput, expectedOutput.length + "line1\nline2\nline3\n".getBytes(StandardCharsets.UTF_16BE).length);
        System.arraycopy("line1\nline2\nline3\n".getBytes(StandardCharsets.UTF_16BE), 0, expectedOutput, 2, "line1\nline2\nline3\n".getBytes(StandardCharsets.UTF_16BE).length);
        assertTrue(Arrays.equals(expectedOutput, outputStream.toByteArray()));
    }

    @Test
    public void testWriteLines_NullOutput() {
        List<String> lines = Arrays.asList("line1", "line2", "line3");
        assertThrows(NullPointerException.class, () -> IOUtils.writeLines(lines, null, (OutputStream) null, StandardCharsets.UTF_8));
    }
}