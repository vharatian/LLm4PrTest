package org.apache.commons.compress.archivers.zip;

import org.apache.commons.compress.AbstractTestCase;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;

public class ZipSplitOutputStreamLLM_Test extends AbstractTestCase {
    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void testNewOutputStreamCreation() throws IOException {
        final File tempFile = File.createTempFile("temp", "zip");
        ZipSplitOutputStream zipSplitOutputStream = new ZipSplitOutputStream(tempFile, 100 * 1024L);
        Assert.assertTrue(Files.exists(tempFile.toPath()));
        zipSplitOutputStream.close();
    }

    @Test
    public void testNewSplitSegmentCreation() throws IOException {
        final File testOutputFile = new File(dir, "testNewSplitSegmentCreation.zip");
        final int splitSize = 100 * 1024;
        final ZipSplitOutputStream zipSplitOutputStream = new ZipSplitOutputStream(testOutputFile, splitSize);
        final File fileToTest = getFile("COMPRESS-477/split_zip_created_by_zip/zip_to_compare_created_by_zip.zip");
        final InputStream inputStream = new FileInputStream(fileToTest);
        final byte[] buffer = new byte[4096];
        int readLen;
        while ((readLen = inputStream.read(buffer)) > 0) {
            zipSplitOutputStream.write(buffer, 0, readLen);
        }
        inputStream.close();
        zipSplitOutputStream.close();

        File zipFile = new File(dir.getPath(), "testNewSplitSegmentCreation.z01");
        Assert.assertTrue(Files.exists(zipFile.toPath()));
        zipFile = new File(dir.getPath(), "testNewSplitSegmentCreation.z02");
        Assert.assertTrue(Files.exists(zipFile.toPath()));
        zipFile = new File(dir.getPath(), "testNewSplitSegmentCreation.z03");
        Assert.assertTrue(Files.exists(zipFile.toPath()));
        zipFile = new File(dir.getPath(), "testNewSplitSegmentCreation.z04");
        Assert.assertTrue(Files.exists(zipFile.toPath()));
        zipFile = new File(dir.getPath(), "testNewSplitSegmentCreation.z05");
        Assert.assertTrue(Files.exists(zipFile.toPath()));
        zipFile = new File(dir.getPath(), "testNewSplitSegmentCreation.zip");
        Assert.assertTrue(Files.exists(zipFile.toPath()));
    }
}