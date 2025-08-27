package org.apache.commons.imaging;

import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.apache.commons.imaging.common.bytesource.ByteSourceArray;
import org.apache.commons.imaging.common.bytesource.ByteSourceFile;
import org.apache.commons.imaging.formats.bmp.BmpImageParser;
import org.apache.commons.imaging.formats.dcx.DcxImageParser;
import org.apache.commons.imaging.formats.gif.GifImageParser;
import org.apache.commons.imaging.formats.icns.IcnsImageParser;
import org.apache.commons.imaging.formats.ico.IcoImageParser;
import org.apache.commons.imaging.formats.jpeg.JpegImageParser;
import org.apache.commons.imaging.formats.pcx.PcxImageParser;
import org.apache.commons.imaging.formats.png.PngImageParser;
import org.apache.commons.imaging.formats.pnm.PnmImageParser;
import org.apache.commons.imaging.formats.psd.PsdImageParser;
import org.apache.commons.imaging.formats.rgbe.RgbeImageParser;
import org.apache.commons.imaging.formats.tiff.TiffImageParser;
import org.apache.commons.imaging.formats.wbmp.WbmpImageParser;
import org.apache.commons.imaging.formats.xbm.XbmImageParser;
import org.apache.commons.imaging.formats.xpm.XpmImageParser;
import org.junit.jupiter.api.Test;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ImageParserLLM_Test {

    @Test
    public void testGetAllImageParsers() {
        List<ImageParser<?>> parsers = ImageParser.getAllImageParsers();
        assertNotNull(parsers);
        assertTrue(parsers.size() > 0);
        assertTrue(parsers.get(0) instanceof BmpImageParser);
        assertTrue(parsers.get(1) instanceof DcxImageParser);
        assertTrue(parsers.get(2) instanceof GifImageParser);
        assertTrue(parsers.get(3) instanceof IcnsImageParser);
        assertTrue(parsers.get(4) instanceof IcoImageParser);
        assertTrue(parsers.get(5) instanceof JpegImageParser);
        assertTrue(parsers.get(6) instanceof PcxImageParser);
        assertTrue(parsers.get(7) instanceof PngImageParser);
        assertTrue(parsers.get(8) instanceof PnmImageParser);
        assertTrue(parsers.get(9) instanceof PsdImageParser);
        assertTrue(parsers.get(10) instanceof RgbeImageParser);
        assertTrue(parsers.get(11) instanceof TiffImageParser);
        assertTrue(parsers.get(12) instanceof WbmpImageParser);
        assertTrue(parsers.get(13) instanceof XbmImageParser);
        assertTrue(parsers.get(14) instanceof XpmImageParser);
    }

    @Test
    public void testCanAcceptExtension() {
        ImageParser<?> parser = new BmpImageParser();
        assertTrue(parser.canAcceptExtension(new File("test.bmp")));
        assertFalse(parser.canAcceptExtension(new File("test.txt")));
    }

    @Test
    public void testGetMetadataWithParams() throws ImageReadException, IOException {
        ImageParser<?> parser = new BmpImageParser();
        ByteSource byteSource = new ByteSourceArray(new byte[]{});
        assertThrows(ImageReadException.class, () -> parser.getMetadata(byteSource, parser.getDefaultParameters()));
    }

    @Test
    public void testGetImageInfoWithParams() throws ImageReadException, IOException {
        ImageParser<?> parser = new BmpImageParser();
        ByteSource byteSource = new ByteSourceArray(new byte[]{});
        assertThrows(ImageReadException.class, () -> parser.getImageInfo(byteSource, parser.getDefaultParameters()));
    }

    @Test
    public void testGetBufferedImageWithParams() throws ImageReadException, IOException {
        ImageParser<?> parser = new BmpImageParser();
        ByteSource byteSource = new ByteSourceArray(new byte[]{});
        assertThrows(ImageReadException.class, () -> parser.getBufferedImage(byteSource, parser.getDefaultParameters()));
    }

    @Test
    public void testGetImageSizeWithParams() throws ImageReadException, IOException {
        ImageParser<?> parser = new BmpImageParser();
        ByteSource byteSource = new ByteSourceArray(new byte[]{});
        assertThrows(ImageReadException.class, () -> parser.getImageSize(byteSource, parser.getDefaultParameters()));
    }

    @Test
    public void testGetICCProfileBytesWithParams() throws ImageReadException, IOException {
        ImageParser<?> parser = new BmpImageParser();
        ByteSource byteSource = new ByteSourceArray(new byte[]{});
        assertThrows(ImageReadException.class, () -> parser.getICCProfileBytes(byteSource, parser.getDefaultParameters()));
    }
}