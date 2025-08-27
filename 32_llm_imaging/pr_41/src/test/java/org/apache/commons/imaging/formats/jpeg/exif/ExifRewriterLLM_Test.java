package org.apache.commons.imaging.formats.jpeg.exif;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.common.bytesource.ByteSourceArray;
import org.apache.commons.imaging.common.bytesource.ByteSourceFile;
import org.apache.commons.imaging.common.bytesource.ByteSourceInputStream;
import org.apache.commons.imaging.formats.tiff.write.TiffOutputSet;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class ExifRewriterLLM_Test {

    @Test
    public void testRemoveExifMetadata_File_ThrowsExceptions() {
        ExifRewriter exifRewriter = new ExifRewriter();
        File mockFile = mock(File.class);
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        assertThrows(ImageReadException.class, () -> exifRewriter.removeExifMetadata(mockFile, os));
        assertThrows(IOException.class, () -> exifRewriter.removeExifMetadata(mockFile, os));
        assertThrows(ImageWriteException.class, () -> exifRewriter.removeExifMetadata(mockFile, os));
    }

    @Test
    public void testRemoveExifMetadata_ByteArray_ThrowsExceptions() {
        ExifRewriter exifRewriter = new ExifRewriter();
        byte[] mockData = new byte[0];
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        assertThrows(ImageReadException.class, () -> exifRewriter.removeExifMetadata(mockData, os));
        assertThrows(IOException.class, () -> exifRewriter.removeExifMetadata(mockData, os));
        assertThrows(ImageWriteException.class, () -> exifRewriter.removeExifMetadata(mockData, os));
    }

    @Test
    public void testRemoveExifMetadata_InputStream_ThrowsExceptions() {
        ExifRewriter exifRewriter = new ExifRewriter();
        ByteArrayInputStream is = new ByteArrayInputStream(new byte[0]);
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        assertThrows(ImageReadException.class, () -> exifRewriter.removeExifMetadata(is, os));
        assertThrows(IOException.class, () -> exifRewriter.removeExifMetadata(is, os));
        assertThrows(ImageWriteException.class, () -> exifRewriter.removeExifMetadata(is, os));
    }

    @Test
    public void testRemoveExifMetadata_ByteSource_ThrowsExceptions() {
        ExifRewriter exifRewriter = new ExifRewriter();
        ByteSourceArray byteSource = new ByteSourceArray(new byte[0]);
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        assertThrows(ImageReadException.class, () -> exifRewriter.removeExifMetadata(byteSource, os));
        assertThrows(IOException.class, () -> exifRewriter.removeExifMetadata(byteSource, os));
        assertThrows(ImageWriteException.class, () -> exifRewriter.removeExifMetadata(byteSource, os));
    }

    @Test
    public void testUpdateExifMetadataLossless_File_ThrowsExceptions() {
        ExifRewriter exifRewriter = new ExifRewriter();
        File mockFile = mock(File.class);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        TiffOutputSet outputSet = mock(TiffOutputSet.class);

        assertThrows(ImageReadException.class, () -> exifRewriter.updateExifMetadataLossless(mockFile, os, outputSet));
        assertThrows(IOException.class, () -> exifRewriter.updateExifMetadataLossless(mockFile, os, outputSet));
        assertThrows(ImageWriteException.class, () -> exifRewriter.updateExifMetadataLossless(mockFile, os, outputSet));
    }

    @Test
    public void testUpdateExifMetadataLossless_ByteArray_ThrowsExceptions() {
        ExifRewriter exifRewriter = new ExifRewriter();
        byte[] mockData = new byte[0];
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        TiffOutputSet outputSet = mock(TiffOutputSet.class);

        assertThrows(ImageReadException.class, () -> exifRewriter.updateExifMetadataLossless(mockData, os, outputSet));
        assertThrows(IOException.class, () -> exifRewriter.updateExifMetadataLossless(mockData, os, outputSet));
        assertThrows(ImageWriteException.class, () -> exifRewriter.updateExifMetadataLossless(mockData, os, outputSet));
    }

    @Test
    public void testUpdateExifMetadataLossless_InputStream_ThrowsExceptions() {
        ExifRewriter exifRewriter = new ExifRewriter();
        ByteArrayInputStream is = new ByteArrayInputStream(new byte[0]);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        TiffOutputSet outputSet = mock(TiffOutputSet.class);

        assertThrows(ImageReadException.class, () -> exifRewriter.updateExifMetadataLossless(is, os, outputSet));
        assertThrows(IOException.class, () -> exifRewriter.updateExifMetadataLossless(is, os, outputSet));
        assertThrows(ImageWriteException.class, () -> exifRewriter.updateExifMetadataLossless(is, os, outputSet));
    }

    @Test
    public void testUpdateExifMetadataLossless_ByteSource_ThrowsExceptions() {
        ExifRewriter exifRewriter = new ExifRewriter();
        ByteSourceArray byteSource = new ByteSourceArray(new byte[0]);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        TiffOutputSet outputSet = mock(TiffOutputSet.class);

        assertThrows(ImageReadException.class, () -> exifRewriter.updateExifMetadataLossless(byteSource, os, outputSet));
        assertThrows(IOException.class, () -> exifRewriter.updateExifMetadataLossless(byteSource, os, outputSet));
        assertThrows(ImageWriteException.class, () -> exifRewriter.updateExifMetadataLossless(byteSource, os, outputSet));
    }

    @Test
    public void testUpdateExifMetadataLossy_File_ThrowsExceptions() {
        ExifRewriter exifRewriter = new ExifRewriter();
        File mockFile = mock(File.class);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        TiffOutputSet outputSet = mock(TiffOutputSet.class);

        assertThrows(ImageReadException.class, () -> exifRewriter.updateExifMetadataLossy(mockFile, os, outputSet));
        assertThrows(IOException.class, () -> exifRewriter.updateExifMetadataLossy(mockFile, os, outputSet));
        assertThrows(ImageWriteException.class, () -> exifRewriter.updateExifMetadataLossy(mockFile, os, outputSet));
    }

    @Test
    public void testUpdateExifMetadataLossy_ByteArray_ThrowsExceptions() {
        ExifRewriter exifRewriter = new ExifRewriter();
        byte[] mockData = new byte[0];
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        TiffOutputSet outputSet = mock(TiffOutputSet.class);

        assertThrows(ImageReadException.class, () -> exifRewriter.updateExifMetadataLossy(mockData, os, outputSet));
        assertThrows(IOException.class, () -> exifRewriter.updateExifMetadataLossy(mockData, os, outputSet));
        assertThrows(ImageWriteException.class, () -> exifRewriter.updateExifMetadataLossy(mockData, os, outputSet));
    }

    @Test
    public void testUpdateExifMetadataLossy_InputStream_ThrowsExceptions() {
        ExifRewriter exifRewriter = new ExifRewriter();
        ByteArrayInputStream is = new ByteArrayInputStream(new byte[0]);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        TiffOutputSet outputSet = mock(TiffOutputSet.class);

        assertThrows(ImageReadException.class, () -> exifRewriter.updateExifMetadataLossy(is, os, outputSet));
        assertThrows(IOException.class, () -> exifRewriter.updateExifMetadataLossy(is, os, outputSet));
        assertThrows(ImageWriteException.class, () -> exifRewriter.updateExifMetadataLossy(is, os, outputSet));
    }

    @Test
    public void testUpdateExifMetadataLossy_ByteSource_ThrowsExceptions() {
        ExifRewriter exifRewriter = new ExifRewriter();
        ByteSourceArray byteSource = new ByteSourceArray(new byte[0]);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        TiffOutputSet outputSet = mock(TiffOutputSet.class);

        assertThrows(ImageReadException.class, () -> exifRewriter.updateExifMetadataLossy(byteSource, os, outputSet));
        assertThrows(IOException.class, () -> exifRewriter.updateExifMetadataLossy(byteSource, os, outputSet));
        assertThrows(ImageWriteException.class, () -> exifRewriter.updateExifMetadataLossy(byteSource, os, outputSet));
    }
}