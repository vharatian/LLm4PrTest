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

public class NewInitMethodRefFormLLM_Test {

    private NewInitMethodRefForm newInitMethodRefForm;
    private OperandManager mockOperandManager;
    private ByteCode mockByteCode;
    private SegmentConstantPool mockSegmentConstantPool;

    @BeforeEach
    public void setUp() {
        newInitMethodRefForm = new NewInitMethodRefForm(0, "name", new int[]{0});
        mockOperandManager = mock(OperandManager.class);
        mockByteCode = mock(ByteCode.class);
        mockSegmentConstantPool = mock(SegmentConstantPool.class);
        when(mockOperandManager.globalConstantPool()).thenReturn(mockSegmentConstantPool);
    }

    @Test
    public void testSetNestedEntries() throws Pack200Exception {
        String context = "context";
        when(mockOperandManager.getNewClass()).thenReturn(context);
        ClassFileEntry mockClassFileEntry = mock(ClassFileEntry.class);
        when(mockSegmentConstantPool.getInitMethodPoolEntry(SegmentConstantPool.CP_METHOD, 0, context))
            .thenReturn(mockClassFileEntry);

        newInitMethodRefForm.setNestedEntries(mockByteCode, mockOperandManager, 0);

        verify(mockByteCode).setNested(new ClassFileEntry[]{mockClassFileEntry});
        verify(mockByteCode).setNestedPositions(new int[][]{{0, 2}});
    }
}