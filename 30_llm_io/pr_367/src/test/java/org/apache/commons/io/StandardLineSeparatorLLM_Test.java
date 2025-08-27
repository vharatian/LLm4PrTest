package org.apache.commons.io;

import static org.apache.commons.io.StandardLineSeparator.CR;
import static org.apache.commons.io.StandardLineSeparator.CRLF;
import static org.apache.commons.io.StandardLineSeparator.LF;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;

public class StandardLineSeparatorLLM_Test {

    @Test
    public void testCR() {
        assertEquals("\r", CR.getString());
    }

    @Test
    public void testCR_getBytes() {
        assertArrayEquals("\r".getBytes(StandardCharsets.ISO_8859_1), CR.getBytes(StandardCharsets.ISO_8859_1));
    }

    @Test
    public void testCRLF() {
        assertEquals("\r\n", CRLF.getString());
    }

    @Test
    public void testCRLF_getBytes() {
        assertArrayEquals("\r\n".getBytes(StandardCharsets.ISO_8859_1), CRLF.getBytes(StandardCharsets.ISO_8859_1));
    }

    @Test
    public void testLF() {
        assertEquals("\n", LF.getString());
    }

    @Test
    public void testLF_getBytes() {
        assertArrayEquals("\n".getBytes(StandardCharsets.ISO_8859_1), LF.getBytes(StandardCharsets.ISO_8859_1));
    }
}