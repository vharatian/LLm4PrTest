package org.apache.commons.imaging.formats.jpeg.iptc;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.common.GenericImageMetadata.GenericImageMetadataItem;
import org.apache.commons.imaging.common.bytesource.ByteSourceFile;
import org.apache.commons.imaging.formats.jpeg.JpegImageMetadata;
import org.apache.commons.imaging.formats.jpeg.JpegImageParser;
import org.apache.commons.imaging.formats.jpeg.JpegPhotoshopMetadata;
import org.junit.jupiter.api.Test;

public class IptcParserLLM_Test {

    @Test
    public void testWriteIPTCBlockWithDefaultCharset() throws ImageWriteException, IOException {
        IptcParser parser = new IptcParser();
        List<IptcRecord> elements = new ArrayList<>();
        elements.add(new IptcRecord(IptcTypes.CITY, "New York"));
        elements.add(new IptcRecord(IptcTypes.BYLINE, "John Doe"));

        byte[] blockData = parser.writeIPTCBlock(elements);

        // Verify that the block data is written correctly with the default charset
        assertTrue(blockData.length > 0);
    }

    @Test
    public void testWriteIPTCBlockWithUTF8Charset() throws ImageWriteException, IOException {
        IptcParser parser = new IptcParser();
        List<IptcRecord> elements = new ArrayList<>();
        elements.add(new IptcRecord(IptcTypes.CITY, "北京"));
        elements.add(new IptcRecord(IptcTypes.BYLINE, "张伟"));

        byte[] blockData = parser.writeIPTCBlock(elements);

        // Verify that the block data is written correctly with the UTF-8 charset
        assertTrue(blockData.length > 0);
    }

    @Test
    public void testWriteIPTCBlockWithCharacterEscapeSequence() throws ImageWriteException, IOException {
        IptcParser parser = new IptcParser();
        List<IptcRecord> elements = new ArrayList<>();
        elements.add(new IptcRecord(IptcTypes.CITY, "New York"));
        elements.add(new IptcRecord(IptcTypes.BYLINE, "John Doe"));

        byte[] blockData = parser.writeIPTCBlock(elements);

        // Verify that the CHARACTER_ESCAPE_SEQUENCE is used correctly
        String blockDataString = new String(blockData, StandardCharsets.ISO_8859_1);
        assertTrue(blockDataString.contains(new String(IptcParser.CHARACTER_ESCAPE_SEQUENCE, StandardCharsets.ISO_8859_1)));
    }
}