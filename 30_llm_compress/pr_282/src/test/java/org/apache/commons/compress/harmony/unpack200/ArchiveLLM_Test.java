package org.apache.commons.compress.harmony.unpack200;

import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class ArchiveLLM_Test {

    private Archive archive;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() throws IOException {
        InputStream inputStream = new ByteArrayInputStream(new byte[]{});
        outputStream = new ByteArrayOutputStream();
        archive = new Archive(inputStream, new JarOutputStream(outputStream));
    }

    @Test
    public void testUnpackWithMagicWord() throws IOException, Pack200Exception {
        byte[] magicWord = new byte[]{(byte) 0xCA, (byte) 0xFE, (byte) 0xD0, (byte) 0x0D};
        InputStream inputStream = new ByteArrayInputStream(magicWord);
        archive = new Archive(inputStream, new JarOutputStream(outputStream));
        
        assertDoesNotThrow(() -> archive.unpack());
    }

    @Test
    public void testUnpackWithNonMagicWord() throws IOException, Pack200Exception {
        byte[] nonMagicWord = new byte[]{(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00};
        InputStream inputStream = new ByteArrayInputStream(nonMagicWord);
        archive = new Archive(inputStream, new JarOutputStream(outputStream));
        
        assertDoesNotThrow(() -> archive.unpack());
    }
}