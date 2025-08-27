package org.apache.commons.imaging.formats.gif;

import org.apache.commons.imaging.common.ImageMetadata;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GifImageMetadataItemLLM_Test {

    @Test
    public void testGetDelay() {
        GifImageMetadataItem item = new GifImageMetadataItem(100, 10, 20);
        assertEquals(100, item.getDelay());
    }

    @Test
    public void testGetLeftPosition() {
        GifImageMetadataItem item = new GifImageMetadataItem(100, 10, 20);
        assertEquals(10, item.getLeftPosition());
    }

    @Test
    public void testGetTopPosition() {
        GifImageMetadataItem item = new GifImageMetadataItem(100, 10, 20);
        assertEquals(20, item.getTopPosition());
    }

    @Test
    public void testToStringWithPrefix() {
        GifImageMetadataItem item = new GifImageMetadataItem(100, 10, 20);
        String expected = "PrefixDelay: 100" + System.getProperty("line.separator") +
                          "PrefixLeft position: 10" + System.getProperty("line.separator") +
                          "PrefixTop position: 20" + System.getProperty("line.separator");
        assertEquals(expected, item.toString("Prefix"));
    }

    @Test
    public void testToStringWithoutPrefix() {
        GifImageMetadataItem item = new GifImageMetadataItem(100, 10, 20);
        String expected = "Delay: 100" + System.getProperty("line.separator") +
                          "Left position: 10" + System.getProperty("line.separator") +
                          "Top position: 20" + System.getProperty("line.separator");
        assertEquals(expected, item.toString(null));
    }
}