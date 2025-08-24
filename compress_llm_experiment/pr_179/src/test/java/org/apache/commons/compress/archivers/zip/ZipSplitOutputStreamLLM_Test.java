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
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void testInitialValues() throws IOException {
        final long splitSize = 100 * 1024;
        final ZipSplitOutputStream output = new ZipSplitOutputStream(File.createTempFile("temp", "zip"), splitSize);
        Assert.assertEquals(0, output.getCurrentSplitSegmentIndex());
        Assert.assertEquals(0, output.getCurrentSplitSegmentBytesWritten());
    }

    @Test
    public void testOpenNewSplitSegment() throws IOException {
        final File testOutputFile = new File(dir, "testOpenNewSplitSegment.zip");
        final int splitSize = 100 * 1024;
        final ZipSplitOutputStream zipSplitOutputStream = new ZipSplitOutputStream(testOutputFile, splitSize);

        // Write data to exceed the split size and trigger segment creation
        final byte[] buffer = new byte[splitSize + 1];
        zipSplitOutputStream.write(buffer);

        // Check if a new segment is created
        Assert.assertEquals(1, zipSplitOutputStream.getCurrentSplitSegmentIndex());
        Assert.assertEquals(1, zipSplitOutputStream.getCurrentSplitSegmentBytesWritten());

        zipSplitOutputStream.close();
    }

    @Test
    public void testFinishThrowsIfAlreadyFinished() throws IOException {
        final File testOutputFile = new File(dir, "testFinishThrowsIfAlreadyFinished.zip");
        final int splitSize = 100 * 1024;
        final ZipSplitOutputStream zipSplitOutputStream = new ZipSplitOutputStream(testOutputFile, splitSize);

        zipSplitOutputStream.close();

        thrown.expect(IOException.class);
        thrown.expectMessage("This archive has already been finished");
        zipSplitOutputStream.close();
    }
}