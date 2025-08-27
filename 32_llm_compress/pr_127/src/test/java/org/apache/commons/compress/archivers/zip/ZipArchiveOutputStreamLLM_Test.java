package org.apache.commons.compress.archivers.zip;

import org.apache.commons.compress.utils.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ZipArchiveOutputStreamLLM_Test {

    private ByteArrayOutputStream byteArrayOutputStream;
    private ZipArchiveOutputStream zipArchiveOutputStream;

    @BeforeEach
    public void setUp() {
        byteArrayOutputStream = new ByteArrayOutputStream();
        zipArchiveOutputStream = new ZipArchiveOutputStream(byteArrayOutputStream);
    }

    @Test
    public void testWritePreamble() throws IOException {
        byte[] preamble = "This is a preamble".getBytes();
        zipArchiveOutputStream.writePreamble(preamble);
        zipArchiveOutputStream.close();

        byte[] writtenData = byteArrayOutputStream.toByteArray();
        assertTrue(new String(writtenData).startsWith("This is a preamble"));
    }

    @Test
    public void testWritePreambleWithOffsetAndLength() throws IOException {
        byte[] preamble = "This is a preamble".getBytes();
        zipArchiveOutputStream.writePreamble(preamble, 5, 9);
        zipArchiveOutputStream.close();

        byte[] writtenData = byteArrayOutputStream.toByteArray();
        assertTrue(new String(writtenData).startsWith("is a pream"));
    }

    @Test
    public void testWritePreambleThrowsExceptionWhenEntryExists() throws IOException {
        zipArchiveOutputStream.putArchiveEntry(new ZipArchiveEntry("testEntry"));
        byte[] preamble = "This is a preamble".getBytes();

        assertThrows(IllegalStateException.class, () -> zipArchiveOutputStream.writePreamble(preamble));
    }
}