package org.apache.commons.compress.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import org.apache.commons.compress.utils.SeekableInMemoryByteChannel;
import org.junit.Assert;
import org.junit.Test;

public class IOUtilsLLM_Test {

    @Test
    public void readRangeFromChannelDoesntReadMoreThanAskedForWithLimit() throws IOException {
        try (ReadableByteChannel in = new SeekableInMemoryByteChannel(new byte[] { 1, 2, 3, 4, 5 })) {
            byte[] read = IOUtils.readRange(in, 3);
            Assert.assertArrayEquals(new byte[] { 1, 2, 3 }, read);
            final ByteBuffer b = ByteBuffer.allocate(1);
            Assert.assertEquals(1, in.read(b));
            Assert.assertArrayEquals(new byte[] { 4 }, b.array());
        }
    }

    @Test
    public void readRangeFromChannelStopsIfThereIsNothingToReadAnymoreWithLimit() throws IOException {
        try (ReadableByteChannel in = new SeekableInMemoryByteChannel(new byte[] { 1, 2, 3, 4, 5 })) {
            byte[] read = IOUtils.readRange(in, 10);
            Assert.assertArrayEquals(new byte[] { 1, 2, 3, 4, 5 }, read);
            final ByteBuffer b = ByteBuffer.allocate(1);
            Assert.assertEquals(-1, in.read(b));
        }
    }
}