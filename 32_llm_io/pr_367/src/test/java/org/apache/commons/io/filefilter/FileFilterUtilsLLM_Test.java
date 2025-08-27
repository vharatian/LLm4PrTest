package org.apache.commons.io.filefilter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FileFilterUtilsLLM_Test {

    @Test
    public void testMakeCVSAwareWithNullFilter() {
        IOFileFilter result = FileFilterUtils.makeCVSAware(null);
        assertNotNull(result);
        assertTrue(result instanceof NotFileFilter);
    }

    @Test
    public void testMakeCVSAwareWithNonNullFilter() {
        IOFileFilter mockFilter = FileFilterUtils.trueFileFilter();
        IOFileFilter result = FileFilterUtils.makeCVSAware(mockFilter);
        assertNotNull(result);
        assertTrue(result instanceof AndFileFilter);
    }
}