package org.apache.commons.imaging.formats.bmp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.bytesource.ByteSourceFile;
import org.junit.jupiter.api.Test;

public class BmpImageParserLLM_Test {

    @Test
    public void testGetImageSizeWithoutHeaderCheck() throws ImageReadException, IOException {
        final String file = "/images/bmp/valid-bmp-file.bmp";
        final File bmp = new File(BmpImageParser.class.getResource(file).getFile());
        final BmpImageParser parser = new BmpImageParser();
        final ByteSourceFile byteSource = new ByteSourceFile(bmp);
        assertEquals(new Dimension(100, 100), parser.getImageSize(byteSource, Collections.emptyMap()), "Expected image size to be 100x100");
    }

    @Test
    public void testGetImageInfoWithoutHeaderCheck() throws ImageReadException, IOException {
        final String file = "/images/bmp/valid-bmp-file.bmp";
        final File bmp = new File(BmpImageParser.class.getResource(file).getFile());
        final BmpImageParser parser = new BmpImageParser();
        final ByteSourceFile byteSource = new ByteSourceFile(bmp);
        final ImageInfo imageInfo = parser.getImageInfo(byteSource, Collections.emptyMap());
        assertEquals(100, imageInfo.getWidth(), "Expected image width to be 100");
        assertEquals(100, imageInfo.getHeight(), "Expected image height to be 100");
    }

    @Test
    public void testGetBufferedImageWithoutHeaderCheck() throws ImageReadException, IOException {
        final String file = "/images/bmp/valid-bmp-file.bmp";
        final File bmp = new File(BmpImageParser.class.getResource(file).getFile());
        final BmpImageParser parser = new BmpImageParser();
        final ByteSourceFile byteSource = new ByteSourceFile(bmp);
        assertNotNull(parser.getBufferedImage(byteSource, Collections.emptyMap()), "Expected BufferedImage to be not null");
    }
}