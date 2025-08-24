package org.apache.commons.compress.archivers.sevenz;

import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import javax.crypto.Cipher;
import org.apache.commons.compress.AbstractTestCase;
import org.apache.commons.compress.MemoryLimitException;
import org.apache.commons.compress.PasswordRequiredException;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.compress.utils.MultiReadOnlySeekableByteChannel;
import org.apache.commons.compress.utils.SeekableInMemoryByteChannel;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class SevenZFileLLM_Test extends AbstractTestCase {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testReadFilesInfoWithStandardCharsets() throws Exception {
        // Create a temporary 7z file with known content
        File tempFile = new File(dir, "testReadFilesInfoWithStandardCharsets.7z");
        try (SevenZOutputFile out = new SevenZOutputFile(tempFile)) {
            SevenZArchiveEntry entry = out.createArchiveEntry(new File("test.txt"), "test.txt");
            out.putArchiveEntry(entry);
            out.write("Test content".getBytes(StandardCharsets.UTF_8));
            out.closeArchiveEntry();
        }

        // Read the 7z file and verify the file names are read correctly using StandardCharsets.UTF_16LE
        try (SevenZFile sevenZFile = new SevenZFile(tempFile)) {
            SevenZArchiveEntry entry = sevenZFile.getNextEntry();
            assertNotNull(entry);
            assertEquals("test.txt", entry.getName());
        }
    }
}