package org.apache.commons.imaging.formats.pcx;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PcxImageParserLLM_Test {

    @Test
    public void testReadPcxHeader() throws IOException, ImageReadException {
        PcxImageParser parser = new PcxImageParser();
        ByteSource byteSource = new ByteSource() {
            @Override
            public InputStream getInputStream() throws IOException {
                return getClass().getResourceAsStream("/test-pcx.pcx");
            }

            @Override
            public byte[] getBlock(long start, int length) throws IOException {
                return new byte[0];
            }

            @Override
            public long getLength() throws IOException {
                return 0;
            }

            @Override
            public String getDescription() {
                return "Test ByteSource";
            }
        };

        PcxImageParser.PcxHeader header = parser.readPcxHeader(byteSource);
        assertNotNull(header);
        assertEquals(10, header.manufacturer);
    }

    @Test
    public void testGetImageSize() throws IOException, ImageReadException {
        PcxImageParser parser = new PcxImageParser();
        ByteSource byteSource = new ByteSource() {
            @Override
            public InputStream getInputStream() throws IOException {
                return getClass().getResourceAsStream("/test-pcx.pcx");
            }

            @Override
            public byte[] getBlock(long start, int length) throws IOException {
                return new byte[0];
            }

            @Override
            public long getLength() throws IOException {
                return 0;
            }

            @Override
            public String getDescription() {
                return "Test ByteSource";
            }
        };

        Map<String, Object> params = new HashMap<>();
        Dimension size = parser.getImageSize(byteSource, params);
        assertNotNull(size);
        assertTrue(size.width > 0);
        assertTrue(size.height > 0);
    }

    @Test
    public void testGetBufferedImage() throws IOException, ImageReadException {
        PcxImageParser parser = new PcxImageParser();
        ByteSource byteSource = new ByteSource() {
            @Override
            public InputStream getInputStream() throws IOException {
                return getClass().getResourceAsStream("/test-pcx.pcx");
            }

            @Override
            public byte[] getBlock(long start, int length) throws IOException {
                return new byte[0];
            }

            @Override
            public long getLength() throws IOException {
                return 0;
            }

            @Override
            public String getDescription() {
                return "Test ByteSource";
            }
        };

        Map<String, Object> params = new HashMap<>();
        BufferedImage image = parser.getBufferedImage(byteSource, params);
        assertNotNull(image);
        assertEquals(BufferedImage.TYPE_BYTE_BINARY, image.getType());
    }
}