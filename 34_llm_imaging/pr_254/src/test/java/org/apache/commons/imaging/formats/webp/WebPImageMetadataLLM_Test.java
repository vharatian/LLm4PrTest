package org.apache.commons.imaging.formats.webp;

import org.apache.commons.imaging.formats.tiff.TiffImageMetadata;
import org.apache.commons.imaging.common.ImageMetadata.ImageMetadataItem;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class WebPImageMetadataLLM_Test {

    @Test
    public void testGetExif() {
        TiffImageMetadata mockExif = mock(TiffImageMetadata.class);
        WebPImageMetadata metadata = new WebPImageMetadata(mockExif);

        assertEquals(mockExif, metadata.getExif());
    }

    @Test
    public void testGetExifWhenNull() {
        WebPImageMetadata metadata = new WebPImageMetadata(null);

        assertNull(metadata.getExif());
    }

    @Test
    public void testGetItemsWithExif() {
        TiffImageMetadata mockExif = mock(TiffImageMetadata.class);
        ImageMetadataItem mockItem = mock(ImageMetadataItem.class);
        when(mockExif.getItems()).thenReturn(List.of(mockItem));

        WebPImageMetadata metadata = new WebPImageMetadata(mockExif);
        List<? extends ImageMetadataItem> items = metadata.getItems();

        assertTrue(items.contains(mockItem));
    }

    @Test
    public void testGetItemsWithoutExif() {
        WebPImageMetadata metadata = new WebPImageMetadata(null);
        List<? extends ImageMetadataItem> items = metadata.getItems();

        assertTrue(items.isEmpty());
    }
}