package org.apache.commons.compress.archivers.zip;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ResourceAlignmentExtraFieldLLM_Test {

    @Test
    public void testDefaultConstructor() {
        ResourceAlignmentExtraField field = new ResourceAlignmentExtraField();
        assertEquals(0, field.getAlignment());
        assertFalse(field.allowMethodChange());
        assertEquals(0, field.getLocalFileDataLength().getValue());
        assertEquals(0, field.getCentralDirectoryLength().getValue());
    }

    @Test
    public void testConstructorWithAlignment() {
        ResourceAlignmentExtraField field = new ResourceAlignmentExtraField(0x1234);
        assertEquals(0x1234, field.getAlignment());
        assertFalse(field.allowMethodChange());
        assertEquals(2, field.getLocalFileDataLength().getValue());
        assertEquals(2, field.getCentralDirectoryLength().getValue());
    }

    @Test
    public void testConstructorWithAlignmentAndMethodChange() {
        ResourceAlignmentExtraField field = new ResourceAlignmentExtraField(0x1234, true);
        assertEquals(0x1234, field.getAlignment());
        assertTrue(field.allowMethodChange());
        assertEquals(2, field.getLocalFileDataLength().getValue());
        assertEquals(2, field.getCentralDirectoryLength().getValue());
    }

    @Test
    public void testConstructorWithAlignmentMethodChangeAndPadding() {
        ResourceAlignmentExtraField field = new ResourceAlignmentExtraField(0x1234, true, 4);
        assertEquals(0x1234, field.getAlignment());
        assertTrue(field.allowMethodChange());
        assertEquals(6, field.getLocalFileDataLength().getValue());
        assertEquals(2, field.getCentralDirectoryLength().getValue());
    }

    @Test
    public void testPaddingInitialization() {
        ResourceAlignmentExtraField field = new ResourceAlignmentExtraField(0x1234, true, 4);
        assertEquals(4, field.getLocalFileDataLength().getValue() - ResourceAlignmentExtraField.BASE_SIZE);
    }

    @Test
    public void testParseFromLocalFileData() throws Exception {
        ResourceAlignmentExtraField field = new ResourceAlignmentExtraField();
        byte[] data = new byte[] {0x34, 0x12, 0, 0, 0, 0};
        field.parseFromLocalFileData(data, 0, 6);
        assertEquals(0x1234, field.getAlignment());
        assertFalse(field.allowMethodChange());
        assertEquals(4, field.getLocalFileDataLength().getValue() - ResourceAlignmentExtraField.BASE_SIZE);
    }

    @Test
    public void testParseFromCentralDirectoryData() throws Exception {
        ResourceAlignmentExtraField field = new ResourceAlignmentExtraField();
        byte[] data = new byte[] {0x34, 0x12};
        field.parseFromCentralDirectoryData(data, 0, 2);
        assertEquals(0x1234, field.getAlignment());
        assertFalse(field.allowMethodChange());
    }
}