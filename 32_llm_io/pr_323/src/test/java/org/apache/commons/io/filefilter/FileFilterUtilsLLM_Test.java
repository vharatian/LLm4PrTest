package org.apache.commons.io.filefilter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.apache.commons.io.filefilter.FileFilterUtils.*;

public class FileFilterUtilsLLM_Test {

    @Test
    public void testMakeCVSAwareWithNullFilter() {
        IOFileFilter filter = makeCVSAware(null);
        assertNotNull(filter);
        assertTrue(filter instanceof NotFileFilter);
    }

    @Test
    public void testMakeCVSAwareWithNonNullFilter() {
        IOFileFilter nameFilter = nameFileFilter("test");
        IOFileFilter filter = makeCVSAware(nameFilter);
        assertNotNull(filter);
        assertTrue(filter instanceof AndFileFilter);
    }

    @Test
    public void testMakeSVNAwareWithNullFilter() {
        IOFileFilter filter = makeSVNAware(null);
        assertNotNull(filter);
        assertTrue(filter instanceof NotFileFilter);
    }

    @Test
    public void testMakeSVNAwareWithNonNullFilter() {
        IOFileFilter nameFilter = nameFileFilter("test");
        IOFileFilter filter = makeSVNAware(nameFilter);
        assertNotNull(filter);
        assertTrue(filter instanceof AndFileFilter);
    }
}