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

public class InitMethodReferenceFormLLM_Test {

    private InitMethodReferenceForm initMethodReferenceForm;
    private OperandManager operandManager;
    private ByteCode byteCode;
    private SegmentConstantPool segmentConstantPool;

    @BeforeEach
    public void setUp() {
        initMethodReferenceForm = mock(InitMethodReferenceForm.class, CALLS_REAL_METHODS);
        operandManager = mock(OperandManager.class);
        byteCode = mock(ByteCode.class);
        segmentConstantPool = mock(SegmentConstantPool.class);
    }

    @Test
    public void testSetNestedEntries() throws Pack200Exception {
        when(operandManager.globalConstantPool()).thenReturn(segmentConstantPool);
        when(operandManager.nextInitRef()).thenReturn(1);
        when(segmentConstantPool.getInitMethodPoolEntry(SegmentConstantPool.CP_METHOD, 1, initMethodReferenceForm.context(operandManager)))
                .thenReturn(mock(ClassFileEntry.class));

        initMethodReferenceForm.setNestedEntries(byteCode, operandManager, 1);

        verify(byteCode).setNested(any(ClassFileEntry[].class));
        verify(byteCode).setNestedPositions(new int[][]{{0, 2}});
    }
}