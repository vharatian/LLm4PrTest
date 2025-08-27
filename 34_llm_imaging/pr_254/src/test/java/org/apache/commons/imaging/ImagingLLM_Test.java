package org.apache.commons.imaging;

import org.apache.commons.imaging.bytesource.ByteSource;
import org.apache.commons.imaging.common.ImageMetadata;
import org.apache.commons.imaging.icc.IccProfileInfo;
import org.apache.commons.imaging.icc.IccProfileParser;
import org.apache.commons.imaging.internal.ImageParserFactory;
import org.junit.jupiter.api.Test;

import java.awt.Dimension;
import java.awt.color.ICC_Profile;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ImagingLLM_Test {

    @Test
    public void testGuessFormatWebP() throws IOException {
        // Prepare a byte array representing a WebP file with the correct magic numbers
        byte[] webpBytes = new byte[]{
                0x52, 0x49, 0x46, 0x46, // RIFF
                0x00, 0x00, 0x00, 0x00, // File size (dummy)
                0x57, 0x45, 0x42, 0x50  // WEBP
        };

        ByteSource byteSource = ByteSource.array(webpBytes);
        ImageFormat format = Imaging.guessFormat(byteSource);

        assertEquals(ImageFormats.WEBP, format, "The format should be recognized as WEBP");
    }

    @Test
    public void testGuessFormatUnknown() throws IOException {
        // Prepare a byte array representing an unknown file format
        byte[] unknownBytes = new byte[]{
                0x00, 0x00, 0x00, 0x00
        };

        ByteSource byteSource = ByteSource.array(unknownBytes);
        ImageFormat format = Imaging.guessFormat(byteSource);

        assertEquals(ImageFormats.UNKNOWN, format, "The format should be recognized as UNKNOWN");
    }

    @Test
    public void testGuessFormatRiffWithoutWebP() throws IOException {
        // Prepare a byte array representing a RIFF file without WEBP magic numbers
        byte[] riffBytes = new byte[]{
                0x52, 0x49, 0x46, 0x46, // RIFF
                0x00, 0x00, 0x00, 0x00, // File size (dummy)
                0x00, 0x00, 0x00, 0x00  // Not WEBP
        };

        ByteSource byteSource = ByteSource.array(riffBytes);
        ImageFormat format = Imaging.guessFormat(byteSource);

        assertEquals(ImageFormats.UNKNOWN, format, "The format should be recognized as UNKNOWN");
    }
}