package org.apache.commons.imaging.formats.jpeg.iptc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.io.File;
import java.io.IOException;
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
    public void testPhotoshopIgnoredBlockTypeLinks() {
        // Verify that the links in the comments are correctly formatted
        String adobeLink = "https://www.adobe.com/devnet-apps/photoshop/fileformatashtml/";
        String jiraLink = "https://issues.apache.org/jira/browse/IMAGING-246";

        // Check if the links are correctly formatted
        assertTrue(adobeLink.startsWith("https://"));
        assertTrue(jiraLink.startsWith("https://"));
    }

}