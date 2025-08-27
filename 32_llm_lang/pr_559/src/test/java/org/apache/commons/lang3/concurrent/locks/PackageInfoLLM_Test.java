package org.apache.commons.lang3.concurrent.locks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PackageInfoLLM_Test {

    @Test
    public void testPackageInfo() {
        Package pkg = Package.getPackage("org.apache.commons.lang3.concurrent.locks");
        assertNotNull(pkg, "Package should not be null");
        assertEquals("3.11", pkg.getImplementationVersion(), "Implementation version should be 3.11");
        assertEquals("Apache Software Foundation", pkg.getImplementationVendor(), "Implementation vendor should be Apache Software Foundation");
        assertEquals("Apache License, Version 2.0", pkg.getSpecificationVersion(), "Specification version should be Apache License, Version 2.0");
    }
}