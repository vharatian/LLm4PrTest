package org.apache.commons.compress.archivers.zip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import org.apache.commons.compress.AbstractTest;
import org.junit.jupiter.api.Test;

public class ZipSplitOutputStreamLLM_Test extends AbstractTest {

    @Test
    public void testCalculateDiskPosition() throws IOException {
        final File testOutputFile = newTempFile("testCalculateDiskPosition.zip");
        final int splitSize = 100 * 1024;
        try (ZipSplitOutputStream zipSplitOutputStream = new ZipSplitOutputStream(testOutputFile, splitSize)) {
            zipSplitOutputStream.write(new byte[splitSize * 2]);
            long position = zipSplitOutputStream.calculateDiskPosition(1, 50);
            assertEquals(splitSize + 50, position);
        }
    }

    @Test
    public void testCalculateDiskPositionThrowsExceptionForLargeDiskNumber() throws IOException {
        final File testOutputFile = newTempFile("testCalculateDiskPosition.zip");
        final int splitSize = 100 * 1024;
        try (ZipSplitOutputStream zipSplitOutputStream = new ZipSplitOutputStream(testOutputFile, splitSize)) {
            assertThrows(IOException.class, () -> zipSplitOutputStream.calculateDiskPosition(Integer.MAX_VALUE, 0));
        }
    }

    @Test
    public void testWriteFullyAt() throws IOException {
        final File testOutputFile = newTempFile("testWriteFullyAt.zip");
        final int splitSize = 100 * 1024;
        final byte[] data = new byte[splitSize * 2];
        data[splitSize - 1] = 1;
        data[splitSize] = 2;

        try (ZipSplitOutputStream zipSplitOutputStream = new ZipSplitOutputStream(testOutputFile, splitSize)) {
            zipSplitOutputStream.write(data);
            zipSplitOutputStream.writeFullyAt(new byte[]{3, 4}, 0, 2, splitSize - 1);

            Path firstSegment = zipSplitOutputStream.positionToFiles.get(0L);
            Path secondSegment = zipSplitOutputStream.positionToFiles.get((long) splitSize);

            try (InputStream firstSegmentStream = Files.newInputStream(firstSegment);
                 InputStream secondSegmentStream = Files.newInputStream(secondSegment)) {
                byte[] buffer = new byte[splitSize];
                firstSegmentStream.read(buffer);
                assertEquals(1, buffer[splitSize - 1]);
                assertEquals(3, buffer[splitSize - 1]);

                secondSegmentStream.read(buffer);
                assertEquals(2, buffer[0]);
                assertEquals(4, buffer[0]);
            }
        }
    }

    @Test
    public void testWriteToSegment() throws IOException {
        final File testOutputFile = newTempFile("testWriteToSegment.zip");
        final int splitSize = 100 * 1024;
        final byte[] data = new byte[splitSize];
        data[0] = 1;
        data[splitSize - 1] = 2;

        try (ZipSplitOutputStream zipSplitOutputStream = new ZipSplitOutputStream(testOutputFile, splitSize)) {
            zipSplitOutputStream.write(data);
            zipSplitOutputStream.writeToSegment(testOutputFile.toPath(), 0, new byte[]{3, 4}, 0, 2);

            try (InputStream inputStream = Files.newInputStream(testOutputFile.toPath())) {
                byte[] buffer = new byte[splitSize];
                inputStream.read(buffer);
                assertEquals(3, buffer[0]);
                assertEquals(4, buffer[1]);
                assertEquals(2, buffer[splitSize - 1]);
            }
        }
    }
}