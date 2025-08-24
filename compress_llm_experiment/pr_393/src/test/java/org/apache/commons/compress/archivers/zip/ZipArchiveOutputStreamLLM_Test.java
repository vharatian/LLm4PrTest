package org.apache.commons.compress.archivers.zip;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import static org.junit.jupiter.api.Assertions.*;

public class ZipArchiveOutputStreamLLM_Test {

    @Test
    public void testRewriteSizesAndCrcRemovesZip64ExtraField() throws IOException {
        Path tempFile = Files.createTempFile("test", ".zip");
        SeekableByteChannel channel = Files.newByteChannel(tempFile, StandardOpenOption.WRITE, StandardOpenOption.READ);
        ZipArchiveOutputStream zos = new ZipArchiveOutputStream(channel);

        ZipArchiveEntry entry = new ZipArchiveEntry("testEntry");
        entry.setMethod(ZipArchiveOutputStream.DEFLATED);
        entry.setSize(100);
        entry.setCompressedSize(100);
        entry.setCrc(12345L);

        zos.putArchiveEntry(entry);
        zos.write(new byte[100]);
        zos.closeArchiveEntry();

        zos.rewriteSizesAndCrc(false);

        assertNull(entry.getExtraField(Zip64ExtendedInformationExtraField.HEADER_ID));
    }

    @Test
    public void testWritePreambleWithOffsetAndLength() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipArchiveOutputStream zos = new ZipArchiveOutputStream(baos);

        byte[] preamble = "This is a preamble".getBytes();
        zos.writePreamble(preamble, 5, 10);

        byte[] writtenPreamble = baos.toByteArray();
        assertEquals("is a pream", new String(writtenPreamble));
    }
}