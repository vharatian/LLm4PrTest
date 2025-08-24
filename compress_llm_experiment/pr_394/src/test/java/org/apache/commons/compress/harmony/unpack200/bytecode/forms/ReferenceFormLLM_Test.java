package org.apache.commons.compress.harmony.unpack200.bytecode.forms;

import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.unpack200.SegmentConstantPool;
import org.apache.commons.compress.harmony.unpack200.bytecode.ByteCode;
import org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry;
import org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReferenceFormLLM_Test {

    private ReferenceForm referenceForm;
    private OperandManager operandManager;
    private ByteCode byteCode;
    private SegmentConstantPool globalPool;
    private ClassFileEntry classFileEntry;

    @BeforeEach
    public void setUp() {
        referenceForm = mock(ReferenceForm.class, Mockito.CALLS_REAL_METHODS);
        operandManager = mock(OperandManager.class);
        byteCode = mock(ByteCode.class);
        globalPool = mock(SegmentConstantPool.class);
        classFileEntry = mock(ClassFileEntry.class);

        when(operandManager.globalConstantPool()).thenReturn(globalPool);
        when(globalPool.getConstantPoolEntry(Mockito.anyInt(), Mockito.anyInt())).thenReturn(classFileEntry);
    }

    @Test
    public void testSetNestedEntries() throws Pack200Exception {
        when(referenceForm.getPoolID()).thenReturn(1);
        when(referenceForm.getOffset(operandManager)).thenReturn(2);

        referenceForm.setNestedEntries(byteCode, operandManager, 2);

        Mockito.verify(byteCode).setNested(Mockito.any(ClassFileEntry[].class));
        Mockito.verify(byteCode).setNestedPositions(Mockito.any(int[][].class));
    }

    @Test
    public void testSetNestedEntriesThrowsPack200Exception() throws Pack200Exception {
        when(referenceForm.getPoolID()).thenReturn(1);
        when(referenceForm.getOffset(operandManager)).thenReturn(2);
        when(globalPool.getConstantPoolEntry(Mockito.anyInt(), Mockito.anyInt())).thenThrow(new Pack200Exception("Test Exception"));

        assertThrows(Pack200Exception.class, () -> {
            referenceForm.setNestedEntries(byteCode, operandManager, 2);
        });
    }

    @Test
    public void testSetNestedEntriesWithNullEntry() {
        when(referenceForm.getPoolID()).thenReturn(1);
        when(referenceForm.getOffset(operandManager)).thenReturn(2);
        when(globalPool.getConstantPoolEntry(Mockito.anyInt(), Mockito.anyInt())).thenReturn(null);

        assertThrows(NullPointerException.class, () -> {
            referenceForm.setNestedEntries(byteCode, operandManager, 2);
        });
    }
}