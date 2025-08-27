package org.apache.commons.imaging.formats.psd;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PsdImageParserLLM_Test {

    @Test
    public void testGetICCProfileBytes() throws ImageReadException, IOException {
        // Arrange
        PsdImageParser parser = new PsdImageParser();
        ByteSource byteSource = mock(ByteSource.class);
        Map<String, Object> params = mock(Map.class);
        byte[] expectedBytes = new byte[]{1, 2, 3, 4};
        ImageResourceBlock block = new ImageResourceBlock(PsdImageParser.IMAGE_RESOURCE_ID_ICC_PROFILE, new byte[0], expectedBytes);
        List<ImageResourceBlock> blocks = new ArrayList<>();
        blocks.add(block);

        PsdImageParser spyParser = spy(parser);
        doReturn(blocks).when(spyParser).readImageResourceBlocks(byteSource, new int[]{PsdImageParser.IMAGE_RESOURCE_ID_ICC_PROFILE}, 1);

        // Act
        byte[] result = spyParser.getICCProfileBytes(byteSource, params);

        // Assert
        assertNotNull(result);
        assertArrayEquals(expectedBytes, result);
        assertNotSame(expectedBytes, result, "The returned byte array should be a clone, not the same instance");
    }
}