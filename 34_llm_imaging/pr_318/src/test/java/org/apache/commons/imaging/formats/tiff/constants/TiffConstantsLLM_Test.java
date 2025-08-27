package org.apache.commons.imaging.formats.tiff.constants;

import org.junit.Test;
import static org.junit.Assert.*;

public class TiffConstantsLLM_Test {

    @Test
    public void testDefaultTiffByteOrder() {
        assertEquals(ByteOrder.LITTLE_ENDIAN, TiffConstants.DEFAULT_TIFF_BYTE_ORDER);
    }

    @Test
    public void testTiffHeaderSize() {
        assertEquals(8, TiffConstants.TIFF_HEADER_SIZE);
    }

    @Test
    public void testTiffDirectoryHeaderLength() {
        assertEquals(2, TiffConstants.TIFF_DIRECTORY_HEADER_LENGTH);
    }

    @Test
    public void testTiffDirectoryFooterLength() {
        assertEquals(4, TiffConstants.TIFF_DIRECTORY_FOOTER_LENGTH);
    }

    @Test
    public void testTiffEntryLength() {
        assertEquals(12, TiffConstants.TIFF_ENTRY_LENGTH);
    }

    @Test
    public void testTiffEntryMaxValueLength() {
        assertEquals(4, TiffConstants.TIFF_ENTRY_MAX_VALUE_LENGTH);
    }

    @Test
    public void testTiffCompressionUncompressed1() {
        assertEquals(1, TiffConstants.TIFF_COMPRESSION_UNCOMPRESSED_1);
    }

    @Test
    public void testTiffCompressionUncompressed() {
        assertEquals(TiffConstants.TIFF_COMPRESSION_UNCOMPRESSED_1, TiffConstants.TIFF_COMPRESSION_UNCOMPRESSED);
    }

    @Test
    public void testTiffCompressionCcitt1D() {
        assertEquals(2, TiffConstants.TIFF_COMPRESSION_CCITT_1D);
    }

    @Test
    public void testTiffCompressionCcittGroup3() {
        assertEquals(3, TiffConstants.TIFF_COMPRESSION_CCITT_GROUP_3);
    }

    @Test
    public void testTiffCompressionCcittGroup4() {
        assertEquals(4, TiffConstants.TIFF_COMPRESSION_CCITT_GROUP_4);
    }

    @Test
    public void testTiffCompressionLzw() {
        assertEquals(5, TiffConstants.TIFF_COMPRESSION_LZW);
    }

    @Test
    public void testTiffCompressionJpegObsolete() {
        assertEquals(6, TiffConstants.TIFF_COMPRESSION_JPEG_OBSOLETE);
    }

    @Test
    public void testTiffCompressionJpeg() {
        assertEquals(7, TiffConstants.TIFF_COMPRESSION_JPEG);
    }

    @Test
    public void testTiffCompressionUncompressed2() {
        assertEquals(32771, TiffConstants.TIFF_COMPRESSION_UNCOMPRESSED_2);
    }

    @Test
    public void testTiffCompressionPackbits() {
        assertEquals(32773, TiffConstants.TIFF_COMPRESSION_PACKBITS);
    }

    @Test
    public void testTiffCompressionDeflatePkzip() {
        assertEquals(32946, TiffConstants.TIFF_COMPRESSION_DEFLATE_PKZIP);
    }

    @Test
    public void testTiffCompressionDeflateAdobe() {
        assertEquals(8, TiffConstants.TIFF_COMPRESSION_DEFLATE_ADOBE);
    }

    @Test
    public void testTiffFlagT4Options2D() {
        assertEquals(1, TiffConstants.TIFF_FLAG_T4_OPTIONS_2D);
    }

    @Test
    public void testTiffFlagT4OptionsUncompressedMode() {
        assertEquals(2, TiffConstants.TIFF_FLAG_T4_OPTIONS_UNCOMPRESSED_MODE);
    }

    @Test
    public void testTiffFlagT4OptionsFill() {
        assertEquals(4, TiffConstants.TIFF_FLAG_T4_OPTIONS_FILL);
    }

    @Test
    public void testTiffFlagT6OptionsUncompressedMode() {
        assertEquals(2, TiffConstants.TIFF_FLAG_T6_OPTIONS_UNCOMPRESSED_MODE);
    }

    @Test
    public void testTiffLzwCompressionBlockSizeMedium() {
        assertEquals(32768, TiffConstants.TIFF_LZW_COMPRESSION_BLOCK_SIZE_MEDIUM);
    }

    @Test
    public void testTiffLzwCompressionBlockSizeLarge() {
        assertEquals(65536, TiffConstants.TIFF_LZW_COMPRESSION_BLOCK_SIZE_LARGE);
    }

    // New tests for the changes in the diff file
    @Test
    public void testTiffVersionStandard() {
        assertEquals(42, TiffConstants.TIFF_VERSION_STANDARD);
    }

    @Test
    public void testTiffVersionBig() {
        assertEquals(43, TiffConstants.TIFF_VERSION_BIG);
    }

    @Test
    public void testTiffEntryLengthBig() {
        assertEquals(12, TiffConstants.TIFF_ENTRY_LENGTH_BIG);
    }

    @Test
    public void testTiffEntryMaxValueLengthBig() {
        assertEquals(8, TiffConstants.TIFF_ENTRY_MAX_VALUE_LENGTH_BIG);
    }
}