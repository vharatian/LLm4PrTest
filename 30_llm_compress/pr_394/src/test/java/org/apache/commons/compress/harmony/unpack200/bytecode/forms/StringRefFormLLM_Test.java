package org.apache.commons.compress.harmony.unpack200.bytecode.forms;

import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.unpack200.SegmentConstantPool;
import org.apache.commons.compress.harmony.unpack200.bytecode.ByteCode;
import org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry;
import org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StringRefFormLLM_Test {

    private StringRefForm stringRefForm;
    private OperandManager operandManager;
    private ByteCode byteCode;
    private SegmentConstantPool globalPool;
    private ClassFileEntry mockEntry;

    @BeforeEach
    public void setUp() {
        stringRefForm = new StringRefForm(0, "test", new int[]{0});
        operandManager = mock(OperandManager.class);
        byteCode = mock(ByteCode.class);
        globalPool = mock(SegmentConstantPool.class);
        mockEntry = mock(ClassFileEntry.class);

        when(operandManager.globalConstantPool()).thenReturn(globalPool);
        when(globalPool.getValue(anyInt(), anyInt())).thenReturn(mockEntry);
    }

    @Test
    public void testSetNestedEntries() throws Pack200Exception {
        int offset = 1;
        stringRefForm.setNestedEntries(byteCode, operandManager, offset);

        verify(byteCode).setNested(new ClassFileEntry[]{mockEntry});
        verify(byteCode).setNestedPositions(new int[][]{{0, 1}});
    }

    @Test
    public void testSetNestedEntriesWidened() throws Pack200Exception {
        stringRefForm = new StringRefForm(0, "test", new int[]{0}, true);
        int offset = 1;
        stringRefForm.setNestedEntries(byteCode, operandManager, offset);

        verify(byteCode).setNested(new ClassFileEntry[]{mockEntry});
        verify(byteCode).setNestedPositions(new int[][]{{0, 2}});
    }
}