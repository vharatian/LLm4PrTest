package org.apache.commons.io.output;

import static org.apache.commons.io.test.TestUtils.checkFile;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

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
        try (Writer out = Files.newBufferedWriter(encodingFinder.toPath(), StandardOpenOption.CREATE)) {
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
    public void testFileWriterWithEncodingUsingFilesAPI() throws Exception {
        successfulRun(new FileWriterWithEncoding(file2, defaultEncoding));
    }

    @Test
    public void testFileWriterWithEncodingUsingFilesAPI_Charset() throws Exception {
        successfulRun(new FileWriterWithEncoding(file2, Charset.defaultCharset()));
    }

    @Test
    public void testFileWriterWithEncodingUsingFilesAPI_CharsetEncoder() throws Exception {
        final CharsetEncoder enc = Charset.defaultCharset().newEncoder();
        successfulRun(new FileWriterWithEncoding(file2, enc));
    }

    private void successfulRun(final FileWriterWithEncoding fw21) throws Exception {
        try (
            FileWriter fw1 = new FileWriter(file1);
            FileWriterWithEncoding fw2 = fw21
        ) {
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

    @Test
    public void constructor_File_encoding_badEncoding() {
        assertThrows(IOException.class, () -> {
            try (Writer writer = new FileWriterWithEncoding(file1, "BAD-ENCODE")) { }
        });
        assertFalse(file1.exists());
    }

    @Test
    public void constructor_File_directory() {
        assertThrows(IOException.class, () -> {
            try (Writer writer = new FileWriterWithEncoding(temporaryFolder, defaultEncoding)) { }
        });
        assertFalse(file1.exists());
    }

    @Test
    public void constructor_File_nullFile() {
        assertThrows(NullPointerException.class, () -> {
            try (Writer writer = new FileWriterWithEncoding((File) null, defaultEncoding)) { }
        });
        assertFalse(file1.exists());
    }

    @Test
    public void constructor_fileName_nullFile() {
        assertThrows(NullPointerException.class, () -> {
            try (Writer writer = new FileWriterWithEncoding((String) null, defaultEncoding)) { }
        });
        assertFalse(file1.exists());
    }

    @Test
    public void sameEncoding_null_Charset_constructor() throws Exception {
        try {
            successfulRun(new FileWriterWithEncoding(file2, (Charset) null));
            fail();
        } catch (final NullPointerException ignore) {
        }
    }
}