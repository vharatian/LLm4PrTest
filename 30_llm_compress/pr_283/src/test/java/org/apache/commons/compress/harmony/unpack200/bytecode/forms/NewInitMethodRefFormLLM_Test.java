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

public class NewInitMethodRefFormLLM_Test {

    private OperandManager operandManager;
    private ByteCode byteCode;
    private SegmentConstantPool globalPool;
    private NewInitMethodRefForm newInitMethodRefForm;

    @BeforeEach
    public void setUp() {
        operandManager = mock(OperandManager.class);
        byteCode = mock(ByteCode.class);
        globalPool = mock(SegmentConstantPool.class);
        when(operandManager.globalConstantPool()).thenReturn(globalPool);
        newInitMethodRefForm = new NewInitMethodRefForm(0, "name", new int[]{0});
    }

    @Test
    public void testContext() {
        when(operandManager.getNewClass()).thenReturn("TestClass");
        String context = newInitMethodRefForm.context(operandManager);
        assertEquals("TestClass", context);
    }

    @Test
    public void testSetNestedEntries() throws Pack200Exception {
        when(operandManager.getNewClass()).thenReturn("TestClass");
        ClassFileEntry mockEntry = mock(ClassFileEntry.class);
        when(globalPool.getInitMethodPoolEntry(SegmentConstantPool.CP_METHOD, 0, "TestClass")).thenReturn(mockEntry);

        newInitMethodRefForm.setNestedEntries(byteCode, operandManager, 0);

        verify(byteCode).setNested(new ClassFileEntry[]{mockEntry});
        verify(byteCode).setNestedPositions(new int[][]{{0, 2}});
    }
}