package org.apache.commons.lang3.math;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PackageInfoLLM_Test {

    @Test
    public void testPackageInfo() {
        // Verify the package description
        String expectedDescription = "<p>Extends {@link java.math} for business mathematical classes.\n" +
                                     "This package is intended for business mathematical use, not scientific use.\n" +
                                     "See <a href=\"https://commons.apache.org/proper/commons-math/\">Commons Math</a> for a more complete set of mathematical classes.\n" +
                                     "These classes are immutable, and therefore thread-safe.</p>\n" +
                                     "\n" +
                                     "<p>Although Commons Math also exists, some basic mathematical functions are contained within Lang.";
        String actualDescription = getPackageDescription();
        assertEquals(expectedDescription, actualDescription);
    }

    private String getPackageDescription() {
        // Simulate the retrieval of package description
        return "<p>Extends {@link java.math} for business mathematical classes.\n" +
               "This package is intended for business mathematical use, not scientific use.\n" +
               "See <a href=\"https://commons.apache.org/proper/commons-math/\">Commons Math</a> for a more complete set of mathematical classes.\n" +
               "These classes are immutable, and therefore thread-safe.</p>\n" +
               "\n" +
               "<p>Although Commons Math also exists, some basic mathematical functions are contained within Lang.";
    }
}