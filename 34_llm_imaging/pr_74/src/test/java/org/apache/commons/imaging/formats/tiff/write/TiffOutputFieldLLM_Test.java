package org.apache.commons.imaging.formats.tiff.write;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.apache.commons.imaging.formats.tiff.fieldtypes.FieldType;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfo;
import org.junit.jupiter.api.Test;

public class TiffOutputFieldLLM_Test {

    @Test
    public void testSeparateValueItemName() {
        TagInfo tagInfo = new TagInfo("Test Tag", 1, FieldType.BYTE, 1);
        byte[] bytes = new byte[]{0x01, 0x02, 0x03, 0x04};
        TiffOutputField tiffOutputField = new TiffOutputField(tagInfo, FieldType.BYTE, 4, bytes);

        if (!tiffOutputField.isLocalValue()) {
            TiffOutputItem.Value separateValueItem = tiffOutputField.getSeperateValue();
            assertNotNull(separateValueItem);
            assertEquals("Field Separate value (Test Tag)", separateValueItem.getName());
        }
    }
}