package org.apache.commons.compress.harmony.unpack200.bytecode.forms;

import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.unpack200.SegmentConstantPool;
import org.apache.commons.compress.harmony.unpack200.bytecode.ByteCode;
import org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry;
import org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ClassRefFormLLM_Test {

    private OperandManager mockOperandManager;
    private ByteCode mockByteCode;
    private SegmentConstantPool mockSegmentConstantPool;
    private ClassRefForm classRefForm;

    @BeforeEach
    public void setUp() {
        mockOperandManager = mock(OperandManager.class);
        mockByteCode = mock(ByteCode.class);
        mockSegmentConstantPool = mock(SegmentConstantPool.class);
        classRefForm = new ClassRefForm(0, "test", new int[]{});
    }

    @Test
    public void testSetNestedEntriesWithOffsetZero() throws Pack200Exception {
        when(mockOperandManager.globalConstantPool()).thenReturn(mockSegmentConstantPool);
        ClassFileEntry mockClassFileEntry = mock(ClassFileEntry.class);
        when(mockSegmentConstantPool.getClassPoolEntry(anyInt())).thenReturn(mockClassFileEntry);
        when(mockOperandManager.getCurrentClass()).thenReturn(0);

        classRefForm.setNestedEntries(mockByteCode, mockOperandManager, 0);

        verify(mockByteCode).setNested(new ClassFileEntry[]{mockClassFileEntry});
        verify(mockByteCode).setNestedPositions(new int[][]{{0, 2}});
    }

    @Test
    public void testSetNestedEntriesWithNonZeroOffset() throws Pack200Exception {
        classRefForm = spy(new ClassRefForm(0, "test", new int[]{}));
        doNothing().when(classRefForm).setNestedEntries(any(ByteCode.class), any(OperandManager.class), anyInt());

        classRefForm.setNestedEntries(mockByteCode, mockOperandManager, 1);

        verify(classRefForm).setNestedEntries(mockByteCode, mockOperandManager, 0);
    }
}