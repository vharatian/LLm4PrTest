package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

public class SystemUtilsLLM_Test {

    @Test
    public void testJavaVendor() {
        // Test to ensure JAVA_VENDOR is correctly set to SystemProperties.getJavaVendor()
        String expectedJavaVendor = SystemProperties.getJavaVendor();
        assertNotNull(expectedJavaVendor, "SystemProperties.getJavaVendor() should not return null");
        assertEquals(expectedJavaVendor, SystemUtils.JAVA_VENDOR, "JAVA_VENDOR should match SystemProperties.getJavaVendor()");
    }
}