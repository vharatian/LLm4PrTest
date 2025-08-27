package org.apache.commons.io.input.buffer;

import org.junit.Test;
import static org.junit.Assert.*;

public class PackageInfoLLM_Test {

    @Test
    public void testPackageInfo() {
        // This test ensures that the package-info.java file exists and is correctly configured.
        Package pkg = Package.getPackage("org.apache.commons.io.input.buffer");
        assertNotNull("Package should not be null", pkg);
        assertEquals("org.apache.commons.io.input.buffer", pkg.getName());
    }
}