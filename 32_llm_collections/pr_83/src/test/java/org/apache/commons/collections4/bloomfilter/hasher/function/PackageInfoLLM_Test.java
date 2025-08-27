package org.apache.commons.collections4.bloomfilter.hasher.function;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for the package-info.java file in the org.apache.commons.collections4.bloomfilter.hasher.function package.
 */
public class PackageInfoLLM_Test {

    @Test
    public void testPackageInfo() {
        // Verify the package name
        Package pkg = Package.getPackage("org.apache.commons.collections4.bloomfilter.hasher.function");
        assertNotNull("Package should not be null", pkg);

        // Verify the package annotations
        assertNotNull("Package should have annotations", pkg.getAnnotations());

        // Verify the license information
        String license = "Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements.";
        assertTrue("License information should be present", pkg.toString().contains(license));
    }
}