package org.apache.commons.compress.harmony.unpack200;

import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AttributeLayoutLLM_Test {

    @Test
    public void testGetValueWithTypeString() throws Pack200Exception {
        // Arrange
        String name = "TestAttribute";
        int context = AttributeLayout.CONTEXT_CLASS;
        String layout = "KQ";
        int index = 0;
        AttributeLayout attributeLayout = new AttributeLayout(name, context, layout, index);
        long value = 123L;
        SegmentConstantPool mockPool = mock(SegmentConstantPool.class);
        ClassFileEntry mockEntry = mock(ClassFileEntry.class);
        when(mockPool.getValue(SegmentConstantPool.CP_STRING, value)).thenReturn(mockEntry);

        // Act
        ClassFileEntry result = attributeLayout.getValue(value, "Ljava/lang/String;", mockPool);

        // Assert
        assertEquals(mockEntry, result);
        verify(mockPool).getValue(SegmentConstantPool.CP_STRING, value);
    }
}