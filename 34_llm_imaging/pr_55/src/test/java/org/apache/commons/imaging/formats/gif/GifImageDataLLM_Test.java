package org.apache.commons.imaging.formats.gif;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GifImageDataLLM_Test {

    @Test
    void testGifImageDataConstructor() {
        ImageDescriptor descriptor = new ImageDescriptor(/* initialize with appropriate values */);
        GraphicControlExtension gce = new GraphicControlExtension(/* initialize with appropriate values */);
        
        GifImageData gifImageData = new GifImageData(descriptor, gce);
        
        assertNotNull(gifImageData.descriptor);
        assertNotNull(gifImageData.gce);
        assertEquals(descriptor, gifImageData.descriptor);
        assertEquals(gce, gifImageData.gce);
    }
}