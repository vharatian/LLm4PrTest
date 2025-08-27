package org.apache.commons.imaging.formats.jpeg.iptc;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.BinaryFunctions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IptcParserLLM_Test {

    @Test
    public void testParseAllBlocks_skipsIgnoredBlockTypes() throws IOException, ImageReadException {
        IptcParser parser = new IptcParser();
        byte[] bytes = createTestBytesWithIgnoredBlockTypes();
        List<IptcBlock> blocks = parser.parseAllBlocks(bytes, true);

        // Ensure that the blocks with ignored types are not present in the parsed blocks
        for (IptcBlock block : blocks) {
            assertFalse(IptcParser.PHOTOSHOP_IGNORED_BLOCK_TYPE.contains(block.blockType));
        }
    }

    private byte[] createTestBytesWithIgnoredBlockTypes() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BinaryOutputStream bos = new BinaryOutputStream(baos);

        // Write Photoshop Identification String
        JpegConstants.PHOTOSHOP_IDENTIFICATION_STRING.writeTo(bos);

        // Write ignored block types
        for (int blockType : IptcParser.PHOTOSHOP_IGNORED_BLOCK_TYPE) {
            bos.write4Bytes(JpegConstants.CONST_8BIM);
            bos.write2Bytes(blockType);
            bos.write(0); // block name length
            bos.write4Bytes(0); // block size
        }

        // Write a valid block type
        bos.write4Bytes(JpegConstants.CONST_8BIM);
        bos.write2Bytes(1028); // some valid block type
        bos.write(0); // block name length
        bos.write4Bytes(0); // block size

        bos.flush();
        return baos.toByteArray();
    }
}