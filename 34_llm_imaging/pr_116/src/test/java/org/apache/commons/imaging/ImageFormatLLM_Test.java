package org.apache.commons.imaging;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ImageFormatLLM_Test {

    private class TestImageFormat implements ImageFormat {
        @Override
        public String getName() {
            return "TestFormat";
        }

        @Override
        public String[] getExtensions() {
            return new String[]{"test", "tst"};
        }

        @Override
        public String getDefaultExtension() {
            return "test";
        }
    }

    @Test
    public void testGetName() {
        ImageFormat format = new TestImageFormat();
        assertEquals("TestFormat", format.getName());
    }

    @Test
    public void testGetExtensions() {
        ImageFormat format = new TestImageFormat();
        String[] expectedExtensions = {"test", "tst"};
        assertArrayEquals(expectedExtensions, format.getExtensions());
    }

    @Test
    public void testGetDefaultExtension() {
        ImageFormat format = new TestImageFormat();
        assertEquals("test", format.getDefaultExtension());
    }
}