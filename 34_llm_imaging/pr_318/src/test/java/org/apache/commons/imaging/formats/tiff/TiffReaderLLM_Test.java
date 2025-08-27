package org.apache.commons.imaging.formats.tiff;

import org.apache.commons.imaging.ImagingException;
import org.apache.commons.imaging.bytesource.ByteSource;
import org.apache.commons.imaging.common.ByteConversions;
import org.apache.commons.imaging.common.BinaryFunctions;
import org.apache.commons.imaging.FormatCompliance;
import org.apache.commons.imaging.formats.tiff.constants.TiffConstants;
import org.apache.commons.imaging.formats.tiff.fieldtypes.AbstractFieldType;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfoDirectory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TiffReaderLLM_Test {

    @Test
    public void testReadTiffHeaderStandardTiff() throws IOException, ImagingException {
        ByteSource byteSource = mock(ByteSource.class);
        InputStream inputStream = mock(InputStream.class);
        when(byteSource.getInputStream()).thenReturn(inputStream);
        when(inputStream.read()).thenReturn((int) 'I', (int) 'I', 42, 0, 8, 0, 0, 0, 0, 0, 0, 0);

        TiffReader tiffReader = new TiffReader(true);
        TiffHeader tiffHeader = tiffReader.readTiffHeader(byteSource);

        assertEquals(ByteOrder.LITTLE_ENDIAN, tiffHeader.byteOrder);
        assertEquals(42, tiffHeader.tiffVersion);
        assertEquals(8, tiffHeader.offsetToFirstIFD);
        assertFalse(tiffReader.bigTiff);
        assertTrue(tiffReader.standardTiff);
        assertEquals(TiffConstants.TIFF_ENTRY_MAX_VALUE_LENGTH, tiffReader.entryMaxValueLength);
    }

    @Test
    public void testReadTiffHeaderBigTiff() throws IOException, ImagingException {
        ByteSource byteSource = mock(ByteSource.class);
        InputStream inputStream = mock(InputStream.class);
        when(byteSource.getInputStream()).thenReturn(inputStream);
        when(inputStream.read()).thenReturn((int) 'I', (int) 'I', 43, 0, 8, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0);

        TiffReader tiffReader = new TiffReader(true);
        TiffHeader tiffHeader = tiffReader.readTiffHeader(byteSource);

        assertEquals(ByteOrder.LITTLE_ENDIAN, tiffHeader.byteOrder);
        assertEquals(43, tiffHeader.tiffVersion);
        assertEquals(8, tiffHeader.offsetToFirstIFD);
        assertTrue(tiffReader.bigTiff);
        assertFalse(tiffReader.standardTiff);
        assertEquals(TiffConstants.TIFF_ENTRY_MAX_VALUE_LENGTH_BIG, tiffReader.entryMaxValueLength);
    }

    @Test
    public void testReadDirectoryEntryCountStandardTiff() throws IOException, ImagingException {
        ByteSource byteSource = mock(ByteSource.class);
        InputStream inputStream = mock(InputStream.class);
        when(byteSource.getInputStream()).thenReturn(inputStream);
        when(inputStream.read()).thenReturn((int) 'I', (int) 'I', 42, 0, 8, 0, 0, 0, 0, 0, 0, 0, 2, 0);

        TiffReader tiffReader = new TiffReader(true);
        tiffReader.readTiffHeader(byteSource);

        FormatCompliance formatCompliance = mock(FormatCompliance.class);
        TiffReader.Listener listener = mock(TiffReader.Listener.class);
        when(listener.setTiffHeader(any())).thenReturn(true);

        tiffReader.readDirectories(byteSource, formatCompliance, listener);

        verify(inputStream, times(1)).read();
    }

    @Test
    public void testReadDirectoryEntryCountBigTiff() throws IOException, ImagingException {
        ByteSource byteSource = mock(ByteSource.class);
        InputStream inputStream = mock(InputStream.class);
        when(byteSource.getInputStream()).thenReturn(inputStream);
        when(inputStream.read()).thenReturn((int) 'I', (int) 'I', 43, 0, 8, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0);

        TiffReader tiffReader = new TiffReader(true);
        tiffReader.readTiffHeader(byteSource);

        FormatCompliance formatCompliance = mock(FormatCompliance.class);
        TiffReader.Listener listener = mock(TiffReader.Listener.class);
        when(listener.setTiffHeader(any())).thenReturn(true);

        tiffReader.readDirectories(byteSource, formatCompliance, listener);

        verify(inputStream, times(1)).read();
    }
}