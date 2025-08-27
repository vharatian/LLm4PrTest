package org.apache.commons.io.output;

import static org.apache.commons.io.test.TestUtils.checkFile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class FileWriterWithEncodingLLM_Test {

    @TempDir
    public File temporaryFolder;

    private String defaultEncoding;
    private File file1;
    private File file2;
    private String textContent;
    private final char[] anotherTestContent = {'f', 'z', 'x'};

    @BeforeEach
    public void setUp() throws Exception {
        final File encodingFinder = new File(temporaryFolder, "finder.txt");
        try (OutputStreamWriter out = new OutputStreamWriter(Files.newOutputStream(encodingFinder.toPath()))) {
            defaultEncoding = out.getEncoding();
        }
        file1 = new File(temporaryFolder, "testfile1.txt");
        file2 = new File(temporaryFolder, "testfile2.txt");
        final char[] arr = new char[1024];
        final char[] chars = "ABCDEFGHIJKLMNOPQabcdefgihklmnopq".toCharArray();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = chars[i % chars.length];
        }
        textContent = new String(arr);
    }

    @Test
    public void testFileWriterWithEncodingExtendsProxyWriter() throws IOException {
        try (FileWriterWithEncoding writer = new FileWriterWithEncoding(file1, defaultEncoding)) {
            assertTrue(writer instanceof ProxyWriter);
        }
    }

    @Test
    public void testFileWriterWithEncodingInitialization() throws IOException {
        try (FileWriterWithEncoding writer = new FileWriterWithEncoding(file1, defaultEncoding)) {
            writer.write("Test");
            writer.flush();
        }
        assertTrue(file1.exists());
        assertEquals(4, file1.length());
    }

    @Test
    public void testFileWriterWithEncodingAppendMode() throws IOException {
        try (FileWriterWithEncoding writer = new FileWriterWithEncoding(file1, defaultEncoding)) {
            writer.write("Test");
            writer.flush();
        }
        assertEquals(4, file1.length());

        try (FileWriterWithEncoding writer = new FileWriterWithEncoding(file1, defaultEncoding, true)) {
            writer.write("Append");
            writer.flush();
        }
        assertEquals(10, file1.length());
    }

    @Test
    public void testFileWriterWithEncodingOverwriteMode() throws IOException {
        try (FileWriterWithEncoding writer = new FileWriterWithEncoding(file1, defaultEncoding)) {
            writer.write("Test");
            writer.flush();
        }
        assertEquals(4, file1.length());

        try (FileWriterWithEncoding writer = new FileWriterWithEncoding(file1, defaultEncoding, false)) {
            writer.write("Overwrite");
            writer.flush();
        }
        assertEquals(9, file1.length());
    }

    private void successfulRun(final FileWriterWithEncoding fw21) throws Exception {
        try (FileWriter fw1 = new FileWriter(file1); FileWriterWithEncoding fw2 = fw21) {
            writeTestPayload(fw1, fw2);
            checkFile(file1, file2);
        }
        assertTrue(file1.exists());
        assertTrue(file2.exists());
    }

    private void writeTestPayload(final FileWriter fw1, final FileWriterWithEncoding fw2) throws IOException {
        assertTrue(file1.exists());
        assertTrue(file2.exists());
        fw1.write(textContent);
        fw2.write(textContent);
        fw1.write(65);
        fw2.write(65);
        fw1.write(anotherTestContent);
        fw2.write(anotherTestContent);
        fw1.write(anotherTestContent, 1, 2);
        fw2.write(anotherTestContent, 1, 2);
        fw1.write("CAFE", 1, 2);
        fw2.write("CAFE", 1, 2);
        fw1.flush();
        fw2.flush();
    }
}