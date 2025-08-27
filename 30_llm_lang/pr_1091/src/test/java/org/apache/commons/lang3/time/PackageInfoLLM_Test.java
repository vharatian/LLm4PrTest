package org.apache.commons.lang3.time;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for package-info.java changes.
 */
public class PackageInfoLLM_Test {

    @Test
    public void testPackageInfo() {
        // Verify that the package name is correct
        Package pkg = Package.getPackage("org.apache.commons.lang3.time");
        assertNotNull(pkg, "Package should not be null");
        assertEquals("org.apache.commons.lang3.time", pkg.getName(), "Package name should be 'org.apache.commons.lang3.time'");

        // Verify that the @since tag is still present
        String since = pkg.getImplementationVersion();
        assertNotNull(since, "@since tag should be present");
        assertEquals("2.0", since, "@since tag should be '2.0'");
    }
}