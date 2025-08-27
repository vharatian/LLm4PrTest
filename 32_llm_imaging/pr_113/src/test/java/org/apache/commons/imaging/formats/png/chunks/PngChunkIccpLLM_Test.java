package org.apache.commons.imaging.formats.png.chunks;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import org.apache.commons.imaging.ImageReadException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PngChunkIccpLLM_Test {
    private static final int chunkType = 1766015824;

    @Test
    public void testErrorOnNoProfileName() {
        final byte[] data = new byte[0];
        Assertions.assertThrows(ImageReadException.class, () -> {
            new PngChunkIccp(0, chunkType, 0, data);
        });
    }

    @Test
    public void testParsingIccpChunk() throws ImageReadException, IOException {
        final List<Byte> bytes = new ArrayList<>();
        final String profileName = "my-profile-01";
        for (final byte b : profileName.getBytes(StandardCharsets.ISO_8859_1)) {
            bytes.add(b);
        }
        bytes.add((byte) 0);
        bytes.add((byte) 0);
        final byte[] uncompressedData = new byte[100];
        IntStream.range(0, 100).forEach(i -> {
            uncompressedData[i] = (byte) (i + 1);
        });
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(100)) {
            final Deflater def = new Deflater();
            try (DeflaterOutputStream ios = new DeflaterOutputStream(baos, def)) {
                ios.write(uncompressedData);
            }
            baos.flush();
            final byte[] compressedData = baos.toByteArray();
            final byte[] data = new byte[bytes.size() + compressedData.length];
            for (int i = 0; i < bytes.size(); ++i) {
                data[i] = bytes.get(i).byteValue();
            }
            IntStream.range(0, compressedData.length).forEach(i -> {
                data[bytes.size() + i] = compressedData[i];
            });
            final PngChunkIccp chunk = new PngChunkIccp(data.length, chunkType, 0, data);
            assertArrayEquals(uncompressedData, chunk.getUncompressedProfile());
        }
    }
}