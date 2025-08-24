package org.apache.commons.compress.archivers.zip;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import java.util.zip.ZipException;

public class ExtraFieldUtilsLLM_Test {

    @Test
    public void testRegisterClassCastException() {
        try {
            ExtraFieldUtils.register(String.class);
            fail("Expected RuntimeException");
        } catch (RuntimeException e) {
            assertEquals("class java.lang.String doesn't implement ZipExtraField", e.getMessage());
        }
    }

    @Test
    public void testRegisterInstantiationException() {
        try {
            ExtraFieldUtils.register(AbstractZipExtraField.class);
            fail("Expected RuntimeException");
        } catch (RuntimeException e) {
            assertEquals("class org.apache.commons.compress.archivers.zip.AbstractZipExtraField is not a concrete class", e.getMessage());
        }
    }

    @Test
    public void testRegisterIllegalAccessException() {
        try {
            ExtraFieldUtils.register(PrivateConstructorZipExtraField.class);
            fail("Expected RuntimeException");
        } catch (RuntimeException e) {
            assertEquals("class org.apache.commons.compress.archivers.zip.PrivateConstructorZipExtraField's no-arg constructor is not public", e.getMessage());
        }
    }

    // Dummy classes for testing purposes
    public static abstract class AbstractZipExtraField implements ZipExtraField {
        @Override
        public ZipShort getHeaderId() {
            return new ZipShort(0x0000);
        }
        @Override
        public ZipShort getLocalFileDataLength() {
            return new ZipShort(0);
        }
        @Override
        public ZipShort getCentralDirectoryLength() {
            return new ZipShort(0);
        }
        @Override
        public byte[] getLocalFileDataData() {
            return new byte[0];
        }
        @Override
        public byte[] getCentralDirectoryData() {
            return new byte[0];
        }
        @Override
        public void parseFromLocalFileData(byte[] buffer, int offset, int length) throws ZipException {}
        @Override
        public void parseFromCentralDirectoryData(byte[] buffer, int offset, int length) throws ZipException {}
    }

    public static class PrivateConstructorZipExtraField implements ZipExtraField {
        private PrivateConstructorZipExtraField() {}
        @Override
        public ZipShort getHeaderId() {
            return new ZipShort(0x0000);
        }
        @Override
        public ZipShort getLocalFileDataLength() {
            return new ZipShort(0);
        }
        @Override
        public ZipShort getCentralDirectoryLength() {
            return new ZipShort(0);
        }
        @Override
        public byte[] getLocalFileDataData() {
            return new byte[0];
        }
        @Override
        public byte[] getCentralDirectoryData() {
            return new byte[0];
        }
        @Override
        public void parseFromLocalFileData(byte[] buffer, int offset, int length) throws ZipException {}
        @Override
        public void parseFromCentralDirectoryData(byte[] buffer, int offset, int length) throws ZipException {}
    }
}