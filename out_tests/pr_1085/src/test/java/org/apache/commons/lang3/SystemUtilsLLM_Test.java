package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.junit.jupiter.api.Test;

public class SystemUtilsLLM_Test extends AbstractLangTest {

    @Test
    public void test_IS_OS_MAC_OSX_BIG_SUR() {
        final String osName = System.getProperty("os.name");
        final String osVersion = System.getProperty("os.version");
        if (osName != null && osName.startsWith("Mac OS X")) {
            if (osVersion.startsWith("11")) {
                assertTrue(SystemUtils.IS_OS_MAC_OSX_BIG_SUR);
            } else {
                assertFalse(SystemUtils.IS_OS_MAC_OSX_BIG_SUR);
            }
        } else {
            assertFalse(SystemUtils.IS_OS_MAC_OSX_BIG_SUR);
        }
    }

    @Test
    public void test_IS_OS_MAC_OSX_MONTEREY() {
        final String osName = System.getProperty("os.name");
        final String osVersion = System.getProperty("os.version");
        if (osName != null && osName.startsWith("Mac OS X")) {
            if (osVersion.startsWith("12")) {
                assertTrue(SystemUtils.IS_OS_MAC_OSX_MONTEREY);
            } else {
                assertFalse(SystemUtils.IS_OS_MAC_OSX_MONTEREY);
            }
        } else {
            assertFalse(SystemUtils.IS_OS_MAC_OSX_MONTEREY);
        }
    }

    @Test
    public void test_IS_OS_MAC_OSX_VENTURA() {
        final String osName = System.getProperty("os.name");
        final String osVersion = System.getProperty("os.version");
        if (osName != null && osName.startsWith("Mac OS X")) {
            if (osVersion.startsWith("13")) {
                assertTrue(SystemUtils.IS_OS_MAC_OSX_VENTURA);
            } else {
                assertFalse(SystemUtils.IS_OS_MAC_OSX_VENTURA);
            }
        } else {
            assertFalse(SystemUtils.IS_OS_MAC_OSX_VENTURA);
        }
    }
}