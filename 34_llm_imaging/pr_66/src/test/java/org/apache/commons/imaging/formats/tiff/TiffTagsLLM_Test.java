package org.apache.commons.imaging.formats.tiff;

import org.apache.commons.imaging.formats.tiff.taginfos.TagInfo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TiffTagsLLM_Test {

    @Test
    public void testGetTagWithEmptyPossibleMatches() {
        // Test the case where possibleMatches list is empty
        List<TagInfo> emptyList = Collections.emptyList();
        TagInfo result = TiffTags.getTag(0, emptyList);
        assertNull(result, "Expected null when possibleMatches list is empty");
    }
}