package org.apache.commons.compress.utils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.NonWritableChannelException;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MultiReadOnlySeekableByteChannelLLM_Test {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void positionWithChannelNumberAndRelativeOffset() throws IOException {
        SeekableByteChannel channel1 = makeSingle("abc".getBytes(StandardCharsets.UTF_8));
        SeekableByteChannel channel2 = makeSingle("def".getBytes(StandardCharsets.UTF_8));
        SeekableByteChannel channel3 = makeSingle("ghi".getBytes(StandardCharsets.UTF_8));
        MultiReadOnlySeekableByteChannel multiChannel = (MultiReadOnlySeekableByteChannel) MultiReadOnlySeekableByteChannel.forSeekableByteChannels(channel1, channel2, channel3);

        // Position to the start of the second channel with an offset of 1
        multiChannel.position(1, 1);
        Assert.assertEquals(4, multiChannel.position());

        // Read the next byte and check if it's correct
        ByteBuffer buffer = ByteBuffer.allocate(1);
        multiChannel.read(buffer);
        buffer.flip();
        Assert.assertEquals('e', buffer.get());

        // Position to the start of the third channel with an offset of 2
        multiChannel.position(2, 2);
        Assert.assertEquals(8, multiChannel.position());

        // Read the next byte and check if it's correct
        buffer.clear();
        multiChannel.read(buffer);
        buffer.flip();
        Assert.assertEquals('i', buffer.get());
    }

    @Test
    public void positionWithChannelNumberAndRelativeOffsetThrowsOnNegativeChannelNumber() throws IOException {
        SeekableByteChannel channel1 = makeSingle("abc".getBytes(StandardCharsets.UTF_8));
        SeekableByteChannel channel2 = makeSingle("def".getBytes(StandardCharsets.UTF_8));
        MultiReadOnlySeekableByteChannel multiChannel = (MultiReadOnlySeekableByteChannel) MultiReadOnlySeekableByteChannel.forSeekableByteChannels(channel1, channel2);

        thrown.expect(IllegalArgumentException.class);
        multiChannel.position(-1, 1);
    }

    @Test
    public void positionWithChannelNumberAndRelativeOffsetThrowsOnNegativeRelativeOffset() throws IOException {
        SeekableByteChannel channel1 = makeSingle("abc".getBytes(StandardCharsets.UTF_8));
        SeekableByteChannel channel2 = makeSingle("def".getBytes(StandardCharsets.UTF_8));
        MultiReadOnlySeekableByteChannel multiChannel = (MultiReadOnlySeekableByteChannel) MultiReadOnlySeekableByteChannel.forSeekableByteChannels(channel1, channel2);

        thrown.expect(IllegalArgumentException.class);
        multiChannel.position(1, -1);
    }

    private SeekableByteChannel makeSingle(byte[] arr) {
        return new SeekableInMemoryByteChannel(arr);
    }
}