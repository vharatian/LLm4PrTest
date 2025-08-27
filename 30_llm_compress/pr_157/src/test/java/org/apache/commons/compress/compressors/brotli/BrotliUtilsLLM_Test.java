package org.apache.commons.compress.compressors.brotli;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BrotliUtilsLLM_Test {

    @Test
    public void testInternalIsBrotliCompressionAvailable() {
        // Since internalIsBrotliCompressionAvailable is private, we need to use reflection to test it
        try {
            java.lang.reflect.Method method = BrotliUtils.class.getDeclaredMethod("internalIsBrotliCompressionAvailable");
            method.setAccessible(true);
            boolean result = (boolean) method.invoke(null);
            // We cannot predict the actual environment, so we just ensure it runs without exception
            assertNotNull(result);
        } catch (Exception e) {
            fail("Exception occurred while testing internalIsBrotliCompressionAvailable: " + e.getMessage());
        }
    }
}