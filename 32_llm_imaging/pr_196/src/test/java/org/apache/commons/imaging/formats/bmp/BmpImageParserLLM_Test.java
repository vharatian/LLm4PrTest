package org.apache.commons.imaging.formats.bmp;

import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.File;
import java.io.IOException;
import org.apache.commons.imaging.ImageReadException;
import org.junit.jupiter.api.Test;

public class BmpImageParserLLM_Test {

    @Test
    public void testInvalidNegativePaletteLength() throws ImageReadException, IOException {
        final String file = "/images/bmp/IMAGING-280/invalid_negative_palette_length.bmp";
        final File bmp = new File(BmpImageParser.class.getResource(file).getFile());
        final BmpImageParser parser = new BmpImageParser();
        assertThrows(ImageReadException.class, () -> parser.getImageInfo(bmp, new BmpImagingParameters()), "Expected ImageReadException for negative palette length");
    }
}