package org.apache.commons.imaging.formats.png.chunks;

import org.apache.commons.imaging.ImageReadException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class PngChunkIccpLLM_Test {

    private static final Logger LOGGER = Logger.getLogger(PngChunkIccpTest.class.getName());

    @Test
    public void testConstructorWithValidData() throws ImageReadException, IOException {
        byte[] bytes = createValidChunkData();
        PngChunkIccp chunk = new PngChunkIccp(100, 1, 12345, bytes);

        assertEquals("ProfileName", chunk.profileName);
        assertEquals(0, chunk.compressionMethod);
        assertNotNull(chunk.getUncompressedProfile());
    }

    @Test
    public void testConstructorWithNoProfileName() {
        byte[] bytes = new byte[]{0, 0, 0, 0}; // No null terminator for profile name

        assertThrows(ImageReadException.class, () -> {
            new PngChunkIccp(100, 1, 12345, bytes);
        });
    }

    @Test
    public void testGetUncompressedProfile() throws ImageReadException, IOException {
        byte[] bytes = createValidChunkData();
        PngChunkIccp chunk = new PngChunkIccp(100, 1, 12345, bytes);

        byte[] uncompressedProfile = chunk.getUncompressedProfile();
        assertNotNull(uncompressedProfile);
        assertNotSame(uncompressedProfile, chunk.getUncompressedProfile());
    }

    private byte[] createValidChunkData() {
        String profileName = "ProfileName";
        byte[] nameBytes = profileName.getBytes(StandardCharsets.ISO_8859_1);
        byte[] compressedProfile = new byte[]{1, 2, 3, 4, 5}; // Example compressed data

        byte[] bytes = new byte[nameBytes.length + 2 + compressedProfile.length];
        System.arraycopy(nameBytes, 0, bytes, 0, nameBytes.length);
        bytes[nameBytes.length] = 0; // Null terminator
        bytes[nameBytes.length + 1] = 0; // Compression method
        System.arraycopy(compressedProfile, 0, bytes, nameBytes.length + 2, compressedProfile.length);

        return bytes;
    }
}