package org.apache.commons.imaging.formats.webp.chunks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PackageInfoLLM_Test {

    @Test
    public void testPackageInfo() {
        // Verify the package name
        Package pkg = Package.getPackage("org.apache.commons.imaging.formats.webp.chunks");
        assertNotNull(pkg, "Package should not be null");
        
        // Verify the package annotations
        assertEquals("1.0-alpha4", pkg.getImplementationVersion(), "Implementation version should be 1.0-alpha4");
        assertEquals("Apache Software Foundation", pkg.getImplementationVendor(), "Implementation vendor should be Apache Software Foundation");
        assertEquals("Apache License, Version 2.0", pkg.getSpecificationLicense(), "Specification license should be Apache License, Version 2.0");
    }
}