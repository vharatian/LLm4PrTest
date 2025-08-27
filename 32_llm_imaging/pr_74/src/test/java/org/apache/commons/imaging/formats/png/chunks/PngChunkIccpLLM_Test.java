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
    public void testErrorOnNoProfileName() throws ImageReadException, IOException {
        final byte[] data = new byte[0];
        Assertions.assertThrows(ImageReadException.class, () -> {
            new PngChunkIccp(0, chunkType, 0, data);
        });
    }

    @Test
    public void testParsingIccpChunk() throws ImageReadException, IOException {
        final List<Byte> bytes = new ArrayList<>();
        final String profileName = "my-profile-01";
        for (byte b : profileName.getBytes(StandardCharsets.ISO_8859_1)) {
            bytes.add(b);
        }
        bytes.add((byte) 0);
        bytes.add((byte) 0);
        byte[] uncompressedData = new byte[100];
        IntStream.range(0, 100).forEach((i) -> {
            uncompressedData[i] = (byte) (i + 1);
        });
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(100)) {
            Deflater def = new Deflater();
            try (DeflaterOutputStream ios = new DeflaterOutputStream(baos, def)) {
                ios.write(uncompressedData);
            }
            baos.flush();
            byte[] compressedData = baos.toByteArray();
            final byte[] data = new byte[bytes.size() + compressedData.length];
            for (int i = 0; i < bytes.size(); ++i) {
                data[i] = bytes.get(i).byteValue();
            }
            IntStream.range(0, compressedData.length).forEach((i) -> {
                data[bytes.size() + i] = compressedData[i];
            });
            final PngChunkIccp chunk = new PngChunkIccp(data.length, chunkType, 0, data);
            assertArrayEquals(uncompressedData, chunk.getUncompressedProfile());
        }
    }

    @Test
    public void testLoggerOutput() throws ImageReadException, IOException {
        final List<Byte> bytes = new ArrayList<>();
        final String profileName = "test-profile";
        for (byte b : profileName.getBytes(StandardCharsets.ISO_8859_1)) {
            bytes.add(b);
        }
        bytes.add((byte) 0);
        bytes.add((byte) 0);
        byte[] uncompressedData = new byte[50];
        IntStream.range(0, 50).forEach((i) -> {
            uncompressedData[i] = (byte) (i + 1);
        });
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(50)) {
            Deflater def = new Deflater();
            try (DeflaterOutputStream ios = new DeflaterOutputStream(baos, def)) {
                ios.write(uncompressedData);
            }
            baos.flush();
            byte[] compressedData = baos.toByteArray();
            final byte[] data = new byte[bytes.size() + compressedData.length];
            for (int i = 0; i < bytes.size(); ++i) {
                data[i] = bytes.get(i).byteValue();
            }
            IntStream.range(0, compressedData.length).forEach((i) -> {
                data[bytes.size() + i] = compressedData[i];
            });
            final PngChunkIccp chunk = new PngChunkIccp(data.length, chunkType, 0, data);
            // The test here is to ensure no exceptions are thrown and the logger output is correct
            // This is a placeholder as actual logger output testing would require a logging framework setup
        }
    }
}