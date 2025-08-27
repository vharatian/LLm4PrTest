package org.apache.commons.imaging;

import static org.junit.Assert.assertTrue;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.imaging.internal.Debug;
import org.apache.commons.imaging.test.util.FileSystemTraversal;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class ImagingLLM_Test extends ImagingTest {

    @Test
    public void testHasImageFileExtension() throws IOException, ImageReadException {
        File testImage = getTestImage();
        assertTrue(Imaging.hasImageFileExtension(testImage));
    }

    @Test
    public void testGetTestImageByName() throws IOException, ImageReadException {
        File testImage = getTestImageByName("test.jpg");
        assertTrue(testImage.exists());
    }

    @Test
    public void testGetTestImages() throws IOException, ImageReadException {
        List<File> testImages = getTestImages();
        assertTrue(testImages.size() > 0);
    }

    @Test
    public void testGetTestImagesWithFilter() throws IOException, ImageReadException {
        List<File> testImages = getTestImages(new ImageFilter() {
            @Override
            public boolean accept(File file) throws IOException, ImageReadException {
                return file.getName().endsWith(".png");
            }
        });
        assertTrue(testImages.size() > 0);
    }

    @Test
    public void testGetTestImagesWithMax() throws IOException, ImageReadException {
        List<File> testImages = getTestImages(null, 5);
        assertTrue(testImages.size() <= 5);
    }

    @Test
    public void testIsInvalidPNGTestFile() throws IOException, ImageReadException {
        File testImage = getTestImageByName("xinvalid.png");
        assertTrue(isInvalidPNGTestFile(testImage));
    }
}