package org.apache.commons.imaging.formats.tiff;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.ByteConversions;
import org.apache.commons.imaging.formats.tiff.constants.TiffConstants;
import org.apache.commons.imaging.formats.tiff.constants.TiffDirectoryConstants;
import org.apache.commons.imaging.formats.tiff.constants.TiffTagConstants;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfo;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoAscii;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoByte;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoBytes;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoDouble;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoDoubles;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoFloat;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoFloats;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoGpsText;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoLong;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoLongs;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoRational;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoRationals;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoSByte;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoSBytes;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoSLong;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoSLongs;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoSRational;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoSRationals;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoSShort;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoSShorts;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoShort;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoShortOrLong;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoShorts;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoXpString;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.ByteOrder;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TiffDirectoryLLM_Test {

    @Test
    public void testGetByteOrder() {
        ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
        TiffDirectory directory = new TiffDirectory(
                TiffDirectoryConstants.DIRECTORY_TYPE_ROOT,
                Collections.emptyList(),
                0,
                0,
                byteOrder
        );
        assertEquals(byteOrder, directory.getByteOrder());
    }

    @Test
    public void testHasTiffFloatingPointRasterData() throws ImageReadException {
        TiffDirectory directory = mock(TiffDirectory.class);
        when(directory.hasTiffImageData()).thenReturn(true);
        when(directory.getFieldValue(TiffTagConstants.TIFF_TAG_SAMPLE_FORMAT, false))
                .thenReturn(new short[]{TiffTagConstants.SAMPLE_FORMAT_VALUE_IEEE_FLOATING_POINT});

        assertTrue(directory.hasTiffFloatingPointRasterData());

        when(directory.getFieldValue(TiffTagConstants.TIFF_TAG_SAMPLE_FORMAT, false))
                .thenReturn(new short[]{TiffTagConstants.SAMPLE_FORMAT_VALUE_TWOS_COMPLEMENT_SIGNED_INTEGER});

        assertFalse(directory.hasTiffFloatingPointRasterData());
    }

    @Test
    public void testHasTiffRasterData() throws ImageReadException {
        TiffDirectory directory = mock(TiffDirectory.class);
        when(directory.hasTiffImageData()).thenReturn(true);
        when(directory.getFieldValue(TiffTagConstants.TIFF_TAG_SAMPLE_FORMAT, false))
                .thenReturn(new short[]{TiffTagConstants.SAMPLE_FORMAT_VALUE_IEEE_FLOATING_POINT});

        assertTrue(directory.hasTiffRasterData());

        when(directory.getFieldValue(TiffTagConstants.TIFF_TAG_SAMPLE_FORMAT, false))
                .thenReturn(new short[]{TiffTagConstants.SAMPLE_FORMAT_VALUE_TWOS_COMPLEMENT_SIGNED_INTEGER});

        assertTrue(directory.hasTiffRasterData());

        when(directory.getFieldValue(TiffTagConstants.TIFF_TAG_SAMPLE_FORMAT, false))
                .thenReturn(new short[]{});

        assertFalse(directory.hasTiffRasterData());
    }

    @Test
    public void testGetRasterData() throws ImageReadException, IOException {
        TiffImageParser parser = mock(TiffImageParser.class);
        TiffDirectory directory = new TiffDirectory(
                TiffDirectoryConstants.DIRECTORY_TYPE_ROOT,
                Collections.emptyList(),
                0,
                0,
                ByteOrder.BIG_ENDIAN
        );
        Map<String, Object> params = Collections.emptyMap();
        TiffRasterData expectedRasterData = mock(TiffRasterData.class);

        when(parser.getRasterData(directory, ByteOrder.BIG_ENDIAN, params)).thenReturn(expectedRasterData);

        TiffRasterData actualRasterData = directory.getRasterData(params);

        assertEquals(expectedRasterData, actualRasterData);
    }
}