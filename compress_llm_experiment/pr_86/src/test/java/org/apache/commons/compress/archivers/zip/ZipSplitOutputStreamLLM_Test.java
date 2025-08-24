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

public class ZipSplitOutputStreamLLM_Test extends AbstractTestCase {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testFinishThrowsExceptionIfAlreadyFinished() throws IOException {
        File tempFile = File.createTempFile("temp", "zip");
        ZipSplitOutputStream output = new ZipSplitOutputStream(tempFile, 100 * 1024L);
        output.close();
        thrown.expect(IOException.class);
        thrown.expectMessage("This archive has already been finished");
        output.close();
    }

    @Test
    public void testOpenNewSplitSegmentCreatesCorrectFileNames() throws IOException {
        File testOutputFile = new File(dir, "testOpenNewSplitSegment.zip");
        int splitSize = 100 * 1024;
        ZipSplitOutputStream zipSplitOutputStream = new ZipSplitOutputStream(testOutputFile, splitSize);

        // Write enough data to create multiple segments
        byte[] buffer = new byte[splitSize];
        zipSplitOutputStream.write(buffer);
        zipSplitOutputStream.write(buffer);
        zipSplitOutputStream.write(buffer);
        zipSplitOutputStream.close();

        File segment1 = new File(dir.getPath(), "testOpenNewSplitSegment.z01");
        File segment2 = new File(dir.getPath(), "testOpenNewSplitSegment.z02");
        File segment3 = new File(dir.getPath(), "testOpenNewSplitSegment.zip");

        Assert.assertTrue(segment1.exists());
        Assert.assertTrue(segment2.exists());
        Assert.assertTrue(segment3.exists());
    }

    @Test
    public void testWriteHandlesMultipleSegmentsCorrectly() throws IOException {
        File testOutputFile = new File(dir, "testWriteHandlesMultipleSegments.zip");
        int splitSize = 100 * 1024;
        ZipSplitOutputStream zipSplitOutputStream = new ZipSplitOutputStream(testOutputFile, splitSize);

        // Write enough data to create multiple segments
        byte[] buffer = new byte[splitSize];
        zipSplitOutputStream.write(buffer);
        zipSplitOutputStream.write(buffer);
        zipSplitOutputStream.write(buffer);
        zipSplitOutputStream.close();

        File segment1 = new File(dir.getPath(), "testWriteHandlesMultipleSegments.z01");
        File segment2 = new File(dir.getPath(), "testWriteHandlesMultipleSegments.z02");
        File segment3 = new File(dir.getPath(), "testWriteHandlesMultipleSegments.zip");

        Assert.assertEquals(segment1.length(), splitSize);
        Assert.assertEquals(segment2.length(), splitSize);
        Assert.assertEquals(segment3.length(), splitSize);
    }

    @Test
    public void testPrepareToWriteUnsplittableContentHandlesRemainingBytesCorrectly() throws IOException {
        File testOutputFile = new File(dir, "testPrepareToWriteUnsplittableContent.zip");
        int splitSize = 100 * 1024;
        ZipSplitOutputStream zipSplitOutputStream = new ZipSplitOutputStream(testOutputFile, splitSize);

        // Write some data to fill part of the segment
        byte[] buffer = new byte[splitSize / 2];
        zipSplitOutputStream.write(buffer);

        // Prepare to write unsplittable content that fits within the remaining space
        zipSplitOutputStream.prepareToWriteUnsplittableContent(splitSize / 2);

        // Write the unsplittable content
        zipSplitOutputStream.write(buffer);
        zipSplitOutputStream.close();

        File segment1 = new File(dir.getPath(), "testPrepareToWriteUnsplittableContent.zip");
        Assert.assertEquals(segment1.length(), splitSize);
    }
}