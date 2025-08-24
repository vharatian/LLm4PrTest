package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class ClassPathUtilsLLM_Test extends AbstractLangTest {

    /**
     * Test for {@link ClassPathUtils#packageToPath(String)} to ensure it throws
     * NullPointerException when the input path is null.
     */
    @Test
    public void testPackageToPathNull() {
        assertThrows(NullPointerException.class, () -> ClassPathUtils.packageToPath(null));
    }

    /**
     * Test for {@link ClassPathUtils#pathToPackage(String)} to ensure it throws
     * NullPointerException when the input path is null.
     */
    @Test
    public void testPathToPackageNull() {
        assertThrows(NullPointerException.class, () -> ClassPathUtils.pathToPackage(null));
    }
}