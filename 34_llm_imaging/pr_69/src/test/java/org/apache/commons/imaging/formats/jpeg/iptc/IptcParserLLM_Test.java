package org.apache.commons.imaging.formats.jpeg.iptc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.bytesource.ByteSourceFile;
import org.apache.commons.imaging.formats.jpeg.JpegImageMetadata;
import org.apache.commons.imaging.formats.jpeg.JpegImageParser;
import org.apache.commons.imaging.formats.jpeg.JpegPhotoshopMetadata;
import org.junit.jupiter.api.Test;

public class IptcParserLLM_Test {

    @Test
    public void testParsePhotoshopSegmentWithGetBlockData() throws ImageReadException, IOException, NoSuchAlgorithmException {
        final String location = IptcParserTest2.class
                .getResource("/images/jpeg/photoshop/IMAGING-246/FallHarvestKitKat_07610.jpg")
                .getFile();
        final File imageFile = new File(location);
        final JpegImageMetadata metadata = (JpegImageMetadata) new JpegImageParser()
                .getMetadata(new ByteSourceFile(imageFile), new HashMap<>());
        final JpegPhotoshopMetadata photoshopMetadata = metadata.getPhotoshop();
        final PhotoshopApp13Data photoshopApp13Data = photoshopMetadata.photoshopApp13Data;
        final List<IptcRecord> records = photoshopApp13Data.getRecords();

        assertTrue(records.size() > 0, "Records should not be empty");
        for (IptcRecord record : records) {
            assertTrue(record.getValue().length() > 0, "Record value should not be empty");
        }
    }

    @Test
    public void testWritePhotoshopApp13SegmentWithGetters() throws ImageReadException, IOException, NoSuchAlgorithmException, ImageWriteException {
        final String location = IptcParserTest2.class
                .getResource("/images/jpeg/photoshop/IMAGING-246/FallHarvestKitKat_07610.jpg")
                .getFile();
        final File imageFile = new File(location);
        final JpegImageMetadata metadata = (JpegImageMetadata) new JpegImageParser()
                .getMetadata(new ByteSourceFile(imageFile), new HashMap<>());
        final JpegPhotoshopMetadata photoshopMetadata = metadata.getPhotoshop();
        final PhotoshopApp13Data photoshopApp13Data = photoshopMetadata.photoshopApp13Data;

        IptcParser parser = new IptcParser();
        byte[] segmentData = parser.writePhotoshopApp13Segment(photoshopApp13Data);

        assertTrue(segmentData.length > 0, "Segment data should not be empty");
    }
}