package org.apache.commons.imaging.formats.gif;

import org.junit.jupiter.api.Test;
import org.apache.commons.imaging.common.ImageMetadata;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class GifImageMetadataLLM_Test {

    @Test
    public void testGetWidth() {
        GifImageMetadata metadata = new GifImageMetadata(100, 200, Collections.emptyList());
        assertEquals(100, metadata.getWidth());
    }

    @Test
    public void testGetHeight() {
        GifImageMetadata metadata = new GifImageMetadata(100, 200, Collections.emptyList());
        assertEquals(200, metadata.getHeight());
    }

    @Test
    public void testGetItems() {
        GifImageMetadataItem item1 = new GifImageMetadataItem("Item1");
        GifImageMetadataItem item2 = new GifImageMetadataItem("Item2");
        List<GifImageMetadataItem> items = Arrays.asList(item1, item2);
        GifImageMetadata metadata = new GifImageMetadata(100, 200, items);
        assertEquals(items, metadata.getItems());
    }

    @Test
    public void testToString() {
        GifImageMetadataItem item1 = new GifImageMetadataItem("Item1");
        GifImageMetadataItem item2 = new GifImageMetadataItem("Item2");
        List<GifImageMetadataItem> items = Arrays.asList(item1, item2);
        GifImageMetadata metadata = new GifImageMetadata(100, 200, items);
        String expected = "GIF metadata:\nWidth: 100\nHeight: 200\n\nImages:\nItem1\nItem2";
        assertTrue(metadata.toString("").contains(expected));
    }

    @Test
    public void testToStringWithPrefix() {
        GifImageMetadataItem item1 = new GifImageMetadataItem("Item1");
        GifImageMetadataItem item2 = new GifImageMetadataItem("Item2");
        List<GifImageMetadataItem> items = Arrays.asList(item1, item2);
        GifImageMetadata metadata = new GifImageMetadata(100, 200, items);
        String prefix = "Prefix: ";
        String expected = "Prefix: GIF metadata:\nPrefix: Width: 100\nPrefix: Height: 200\n\nPrefix: Images:\nPrefix: Item1\nPrefix: Item2";
        assertTrue(metadata.toString(prefix).contains(expected));
    }
}