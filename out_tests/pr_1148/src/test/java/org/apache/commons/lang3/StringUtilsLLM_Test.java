package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

public class StringUtilsLLM_Test {

    @Test
    public void testAppendIfMissing() {
        assertNull(StringUtils.appendIfMissing(null, null), "appendIfMissing(null,null)");
        assertEquals("abc", StringUtils.appendIfMissing("abc", null), "appendIfMissing(abc,null)");
        assertEquals("xyz", StringUtils.appendIfMissing("", "xyz"), "appendIfMissing(\"\",xyz)");
        assertEquals("abcxyz", StringUtils.appendIfMissing("abc", "xyz"), "appendIfMissing(abc,xyz)");
        assertEquals("abcxyz", StringUtils.appendIfMissing("abcxyz", "xyz"), "appendIfMissing(abcxyz,xyz)");
        assertEquals("aXYZxyz", StringUtils.appendIfMissing("aXYZ", "xyz"), "appendIfMissing(aXYZ,xyz)");
    }

    @Test
    public void testPrependIfMissing() {
        assertNull(StringUtils.prependIfMissing(null, null), "prependIfMissing(null,null)");
        assertEquals("abc", StringUtils.prependIfMissing("abc", null), "prependIfMissing(abc,null)");
        assertEquals("xyz", StringUtils.prependIfMissing("", "xyz"), "prependIfMissing(\"\",xyz)");
        assertEquals("xyzabc", StringUtils.prependIfMissing("abc", "xyz"), "prependIfMissing(abc,xyz)");
        assertEquals("xyzabc", StringUtils.prependIfMissing("xyzabc", "xyz"), "prependIfMissing(xyzabc,xyz)");
        assertEquals("xyzXYZabc", StringUtils.prependIfMissing("XYZabc", "xyz"), "prependIfMissing(XYZabc,xyz)");
    }
}