package org.apache.commons.imaging.formats.tiff.write;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.apache.commons.imaging.formats.tiff.constants.TiffDirectoryConstants;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TiffOutputSetLLM_Test {
    private TiffOutputSet tiffOutputSet;

    @BeforeEach
    public void setUp() throws Exception {
        tiffOutputSet = new TiffOutputSet();
    }

    @Test
    public void testToStringFormat() throws Exception {
        TiffOutputDirectory directory = tiffOutputSet.addRootDirectory();
        TagInfo tagInfo = new TagInfo("TestTag", 1, 1, 1);
        TiffOutputField field = new TiffOutputField(tagInfo, tiffOutputSet.byteOrder, new byte[]{0});
        directory.add(field);

        String output = tiffOutputSet.toString();
        String expectedFieldString = "\t\tfield 0: " + tagInfo;

        assertTrue(output.contains(expectedFieldString), "Output string should contain the formatted field information.");
    }
}