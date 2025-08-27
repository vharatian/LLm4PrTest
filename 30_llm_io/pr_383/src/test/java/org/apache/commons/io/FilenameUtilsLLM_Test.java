package org.apache.commons.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class FilenameUtilsLLM_Test {

    @Test
    public void testNormalizeDoubleSlash() {
        assertEquals("//foo/bar", FilenameUtils.normalize("//foo//./bar"));
    }
}