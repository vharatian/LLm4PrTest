package org.apache.commons.imaging.formats.tiff.fieldtypes;

import org.apache.commons.imaging.ImagingException;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class AbstractFieldTypeLLM_Test {

    @Test
    public void testGetFieldType_Long8() throws ImagingException {
        AbstractFieldType fieldType = AbstractFieldType.getFieldType(16);
        assertTrue(fieldType instanceof FieldTypeLong8);
        assertEquals("Long8", fieldType.getName());
    }

    @Test
    public void testGetFieldType_SLong8() throws ImagingException {
        AbstractFieldType fieldType = AbstractFieldType.getFieldType(17);
        assertTrue(fieldType instanceof FieldTypeLong8);
        assertEquals("Long8", fieldType.getName());
    }

    @Test
    public void testGetFieldType_IFD8() throws ImagingException {
        AbstractFieldType fieldType = AbstractFieldType.getFieldType(18);
        assertTrue(fieldType instanceof FieldTypeLong8);
        assertEquals("Long8", fieldType.getName());
    }

    @Test
    public void testAnyListContainsNewFieldTypes() {
        List<AbstractFieldType> anyList = AbstractFieldType.ANY;
        assertTrue(anyList.contains(AbstractFieldType.LONG8));
        assertTrue(anyList.contains(AbstractFieldType.SLONG8));
        assertTrue(anyList.contains(AbstractFieldType.IFD8));
    }
}