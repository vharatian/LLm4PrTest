package org.apache.commons.imaging.formats.icns;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class IcnsImageParserLLM_Test {

    @Test
    public void testWriteImageWithInvalidDimensions() {
        IcnsImageParser parser = new IcnsImageParser();
        BufferedImage image = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Map<String, Object> params = new HashMap<>();

        assertThrows(ImageWriteException.class, () -> {
            parser.writeImage(image, os, params);
        });
    }

    @Test
    public void testWriteImageWithValidDimensions() throws ImageWriteException, IOException {
        IcnsImageParser parser = new IcnsImageParser();
        BufferedImage image = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Map<String, Object> params = new HashMap<>();

        parser.writeImage(image, os, params);
    }

    @Test
    public void testGetImageInfoWithUnknownParameter() {
        IcnsImageParser parser = new IcnsImageParser();
        ByteSource byteSource = ByteSource.array(new byte[0]);
        Map<String, Object> params = new HashMap<>();
        params.put("unknown_param", "value");

        assertThrows(ImageReadException.class, () -> {
            parser.getImageInfo(byteSource, params);
        });
    }

    @Test
    public void testGetImageSizeWithUnknownParameter() {
        IcnsImageParser parser = new IcnsImageParser();
        ByteSource byteSource = ByteSource.array(new byte[0]);
        Map<String, Object> params = new HashMap<>();
        params.put("unknown_param", "value");

        assertThrows(ImageReadException.class, () -> {
            parser.getImageSize(byteSource, params);
        });
    }
}