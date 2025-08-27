package org.apache.commons.imaging.formats.jpeg.iptc;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IptcBlockLLM_Test {

    @Test
    public void testConstructorWithNullBlockNameBytes() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new IptcBlock(1, null, new byte[]{1, 2, 3});
        });
        assertEquals("Block name bytes must not be null.", exception.getMessage());
    }

    @Test
    public void testConstructorWithNullBlockData() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new IptcBlock(1, new byte[]{1, 2, 3}, null);
        });
        assertEquals("Block data bytes must not be null.", exception.getMessage());
    }

    @Test
    public void testGetBlockType() {
        IptcBlock block = new IptcBlock(1, new byte[]{1, 2, 3}, new byte[]{4, 5, 6});
        assertEquals(1, block.getBlockType());
    }

    @Test
    public void testGetBlockNameBytes() {
        byte[] blockNameBytes = new byte[]{1, 2, 3};
        IptcBlock block = new IptcBlock(1, blockNameBytes, new byte[]{4, 5, 6});
        assertArrayEquals(blockNameBytes, block.getBlockNameBytes());
        assertNotSame(blockNameBytes, block.getBlockNameBytes());
    }

    @Test
    public void testGetBlockData() {
        byte[] blockData = new byte[]{4, 5, 6};
        IptcBlock block = new IptcBlock(1, new byte[]{1, 2, 3}, blockData);
        assertArrayEquals(blockData, block.getBlockData());
        assertNotSame(blockData, block.getBlockData());
    }

    @Test
    public void testIsIPTCBlock() {
        IptcBlock block = new IptcBlock(IptcConstants.IMAGE_RESOURCE_BLOCK_IPTC_DATA, new byte[]{1, 2, 3}, new byte[]{4, 5, 6});
        assertTrue(block.isIPTCBlock());

        IptcBlock nonIptcBlock = new IptcBlock(999, new byte[]{1, 2, 3}, new byte[]{4, 5, 6});
        assertFalse(nonIptcBlock.isIPTCBlock());
    }
}