package org.apache.commons.imaging.formats.tiff;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.ImagingException;
import org.apache.commons.imaging.bytesource.ByteSource;
import org.apache.commons.imaging.common.ImageMetadata;
import org.junit.jupiter.api.Test;

public class TiffImageParserLLM_Test {

    @Test
    public void testGetImageInfoWithColorType() throws IOException, ImagingException {
        TiffImageParser parser = new TiffImageParser();
        ByteSource byteSource = ByteSource.file(new File("src/test/resources/images/tiff/sample.tiff"));
        TiffImagingParameters params = new TiffImagingParameters();

        ImageInfo imageInfo = parser.getImageInfo(byteSource, params);

        assertNotNull(imageInfo);
        assertTrue(imageInfo.getColorType() == ImageInfo.ColorType.RGB 
                || imageInfo.getColorType() == ImageInfo.ColorType.BW 
                || imageInfo.getColorType() == ImageInfo.ColorType.CMYK 
                || imageInfo.getColorType() == ImageInfo.ColorType.YCbCr 
                || imageInfo.getColorType() == ImageInfo.ColorType.UNKNOWN);
    }

    @Test
    public void testGetImageInfoWithTransparency() throws IOException, ImagingException {
        TiffImageParser parser = new TiffImageParser();
        ByteSource byteSource = ByteSource.file(new File("src/test/resources/images/tiff/sample_with_transparency.tiff"));
        TiffImagingParameters params = new TiffImagingParameters();

        ImageInfo imageInfo = parser.getImageInfo(byteSource, params);

        assertNotNull(imageInfo);
        assertTrue(imageInfo.isTransparent());
    }

    @Test
    public void testGetImageInfoWithExtraSamples() throws IOException, ImagingException {
        TiffImageParser parser = new TiffImageParser();
        ByteSource byteSource = ByteSource.file(new File("src/test/resources/images/tiff/sample_with_extra_samples.tiff"));
        TiffImagingParameters params = new TiffImagingParameters();

        ImageInfo imageInfo = parser.getImageInfo(byteSource, params);

        assertNotNull(imageInfo);
        assertEquals(4, imageInfo.getBitsPerPixel());
    }
}