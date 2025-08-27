package org.apache.commons.imaging.formats.tiff;

import org.apache.commons.imaging.ImagingException;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfo;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoLong;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoLongs;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoShort;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoShortOrLong;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoShorts;
import org.junit.jupiter.api.Test;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TiffDirectoryLLM_Test {

    @Test
    public void testGetRawImageDataElements() throws ImagingException {
        // Mock TiffField for offsets
        TiffField offsetsField = mock(TiffField.class);
        when(offsetsField.getLongArrayValue()).thenReturn(new long[]{100L, 200L, 300L});

        // Mock TiffField for byteCounts
        TiffField byteCountsField = mock(TiffField.class);
        when(byteCountsField.getIntArrayValue()).thenReturn(new int[]{10, 20, 30});

        // Create TiffDirectory instance
        TiffDirectory directory = new TiffDirectory(
                TiffDirectoryConstants.DIRECTORY_TYPE_ROOT,
                Collections.emptyList(),
                0L,
                0L,
                ByteOrder.BIG_ENDIAN
        );

        // Invoke the method
        List<TiffDirectory.ImageDataElement> result = directory.getRawImageDataElements(offsetsField, byteCountsField);

        // Verify the result
        assertEquals(3, result.size());
        assertEquals(100L, result.get(0).offset);
        assertEquals(10, result.get(0).length);
        assertEquals(200L, result.get(1).offset);
        assertEquals(20, result.get(1).length);
        assertEquals(300L, result.get(2).offset);
        assertEquals(30, result.get(2).length);
    }
}