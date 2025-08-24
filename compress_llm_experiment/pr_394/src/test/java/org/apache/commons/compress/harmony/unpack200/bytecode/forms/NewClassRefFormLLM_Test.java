package org.apache.commons.compress.harmony.unpack200.bytecode.forms;

import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.unpack200.SegmentConstantPool;
import org.apache.commons.compress.harmony.unpack200.bytecode.ByteCode;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPClass;
import org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry;
import org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class NewClassRefFormLLM_Test {

    private NewClassRefForm newClassRefForm;
    private ByteCode byteCode;
    private OperandManager operandManager;
    private SegmentConstantPool segmentConstantPool;
    private CPClass cpClass;

    @BeforeEach
    public void setUp() {
        newClassRefForm = new NewClassRefForm(0, "test", new int[]{});
        byteCode = mock(ByteCode.class);
        operandManager = mock(OperandManager.class);
        segmentConstantPool = mock(SegmentConstantPool.class);
        cpClass = mock(CPClass.class);
    }

    @Test
    public void testSetByteCodeOperandsWithOffsetZero() {
        when(operandManager.globalConstantPool()).thenReturn(segmentConstantPool);
        when(operandManager.getCurrentClass()).thenReturn(1);
        when(segmentConstantPool.getClassPoolEntry(1)).thenReturn(cpClass);
        when(cpClass.getName()).thenReturn("TestClass");

        newClassRefForm.setByteCodeOperands(byteCode, operandManager, 0);

        verify(byteCode).setNested(any(ClassFileEntry[].class));
        verify(byteCode).setNestedPositions(new int[][]{{0, 2}});
        verify(operandManager).setNewClass("TestClass");
    }

    @Test
    public void testSetByteCodeOperandsWithNonZeroOffset() throws Pack200Exception {
        when(operandManager.globalConstantPool()).thenReturn(segmentConstantPool);
        when(operandManager.getCurrentClass()).thenReturn(1);
        when(segmentConstantPool.getClassPoolEntry(1)).thenReturn(cpClass);
        when(cpClass.getName()).thenReturn("TestClass");

        newClassRefForm.setByteCodeOperands(byteCode, operandManager, 1);

        verify(byteCode, never()).setNested(any(ClassFileEntry[].class));
        verify(byteCode, never()).setNestedPositions(any(int[][].class));
        verify(operandManager).setNewClass("TestClass");
    }
}