package org.apache.commons.text.numbers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the package org.apache.commons.text.numbers.
 */
public class PackageInfoLLM_Test {

    @Test
    public void testPackageInfo() {
        // Verify the package name
        Package pkg = Package.getPackage("org.apache.commons.text.numbers");
        assertNotNull(pkg, "Package org.apache.commons.text.numbers should exist");

        // Verify the package specification title
        assertEquals("Apache Commons Text", pkg.getSpecificationTitle(), "Specification title should be 'Apache Commons Text'");

        // Verify the package specification version
        assertEquals("1.10", pkg.getSpecificationVersion(), "Specification version should be '1.10'");

        // Verify the package specification vendor
        assertEquals("The Apache Software Foundation", pkg.getSpecificationVendor(), "Specification vendor should be 'The Apache Software Foundation'");

        // Verify the package implementation title
        assertEquals("org.apache.commons.text.numbers", pkg.getImplementationTitle(), "Implementation title should be 'org.apache.commons.text.numbers'");

        // Verify the package implementation version
        assertNotNull(pkg.getImplementationVersion(), "Implementation version should not be null");

        // Verify the package implementation vendor
        assertEquals("The Apache Software Foundation", pkg.getImplementationVendor(), "Implementation vendor should be 'The Apache Software Foundation'");
    }
}