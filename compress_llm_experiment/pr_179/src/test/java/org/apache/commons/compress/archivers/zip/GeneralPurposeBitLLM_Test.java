package org.apache.commons.compress.archivers.zip;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import org.junit.Test;

public class GeneralPurposeBitLLM_Test {

    @Test
    public void testDefaultValuesAfterModification() {
        GeneralPurposeBit gpb = new GeneralPurposeBit();
        assertFalse(gpb.usesDataDescriptor());
        assertFalse(gpb.usesUTF8ForNames());
        assertFalse(gpb.usesEncryption());
        assertFalse(gpb.usesStrongEncryption());
    }

    @Test
    public void testEncodeAfterModification() {
        GeneralPurposeBit gpb = new GeneralPurposeBit();
        final byte[] expected = new byte[2];
        assertTrue(Arrays.equals(expected, gpb.encode()));
    }

    @Test
    public void testParseAfterModification() {
        GeneralPurposeBit gpb = GeneralPurposeBit.parse(new byte[2], 0);
        assertFalse(gpb.usesDataDescriptor());
        assertFalse(gpb.usesUTF8ForNames());
        assertFalse(gpb.usesEncryption());
        assertFalse(gpb.usesStrongEncryption());

        gpb = GeneralPurposeBit.parse(new byte[] {(byte) 255, (byte) 255}, 0);
        assertTrue(gpb.usesDataDescriptor());
        assertTrue(gpb.usesUTF8ForNames());
        assertTrue(gpb.usesEncryption());
        assertTrue(gpb.usesStrongEncryption());
    }
}