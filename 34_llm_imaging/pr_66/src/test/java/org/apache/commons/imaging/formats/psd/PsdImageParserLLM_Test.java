package org.apache.commons.imaging.formats.psd;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PsdImageParserLLM_Test {

    @Test
    public void testGetICCProfileBytesWithEmptyBlocks() throws ImageReadException, IOException {
        PsdImageParser parser = new PsdImageParser();
        ByteSource byteSource = mock(ByteSource.class);

        // Mocking readImageResourceBlocks to return an empty list
        PsdImageParser spyParser = spy(parser);
        doReturn(List.of()).when(spyParser).readImageResourceBlocks(byteSource, new int[]{PsdImageParser.IMAGE_RESOURCE_ID_ICC_PROFILE}, 1);

        byte[] result = spyParser.getICCProfileBytes(byteSource, Map.of());
        assertNull(result, "Expected null when blocks are empty");
    }

    @Test
    public void testGetXmpXmlWithEmptyBlocks() throws ImageReadException, IOException {
        PsdImageParser parser = new PsdImageParser();
        ByteSource byteSource = mock(ByteSource.class);

        // Mocking readImageResourceBlocks to return an empty list
        PsdImageParser spyParser = spy(parser);
        doReturn(List.of()).when(spyParser).readImageResourceBlocks(byteSource, new int[]{PsdImageParser.IMAGE_RESOURCE_ID_XMP}, -1);

        String result = spyParser.getXmpXml(byteSource, Map.of());
        assertNull(result, "Expected null when blocks are empty");
    }

    @Test
    public void testGetXmpXmlWithMultipleXmpBlocks() throws ImageReadException, IOException {
        PsdImageParser parser = new PsdImageParser();
        ByteSource byteSource = mock(ByteSource.class);

        // Mocking readImageResourceBlocks to return multiple XMP blocks
        PsdImageParser spyParser = spy(parser);
        ImageResourceBlock block1 = new ImageResourceBlock(PsdImageParser.IMAGE_RESOURCE_ID_XMP, new byte[]{}, new byte[]{});
        ImageResourceBlock block2 = new ImageResourceBlock(PsdImageParser.IMAGE_RESOURCE_ID_XMP, new byte[]{}, new byte[]{});
        doReturn(List.of(block1, block2)).when(spyParser).readImageResourceBlocks(byteSource, new int[]{PsdImageParser.IMAGE_RESOURCE_ID_XMP}, -1);

        assertThrows(ImageReadException.class, () -> spyParser.getXmpXml(byteSource, Map.of()), "Expected ImageReadException when multiple XMP blocks are present");
    }
}