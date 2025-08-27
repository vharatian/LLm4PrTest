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

public class ClassSpecificReferenceFormLLM_Test {

    private ClassSpecificReferenceForm classSpecificReferenceForm;
    private OperandManager operandManager;
    private ByteCode byteCode;
    private SegmentConstantPool segmentConstantPool;
    private ClassFileEntry classFileEntry;

    @BeforeEach
    public void setUp() {
        operandManager = mock(OperandManager.class);
        byteCode = mock(ByteCode.class);
        segmentConstantPool = mock(SegmentConstantPool.class);
        classFileEntry = mock(ClassFileEntry.class);

        when(operandManager.globalConstantPool()).thenReturn(segmentConstantPool);
        when(segmentConstantPool.getClassSpecificPoolEntry(anyInt(), anyInt(), anyString())).thenReturn(classFileEntry);

        classSpecificReferenceForm = new ClassSpecificReferenceForm(0, "test", new int[]{0}) {
            @Override
            protected String context(OperandManager operandManager) {
                return "testContext";
            }

            @Override
            protected int getOffset(OperandManager operandManager) {
                return 0;
            }

            @Override
            protected int getPoolID() {
                return 0;
            }
        };
    }

    @Test
    public void testSetNestedEntries() throws Pack200Exception {
        int offset = 1;

        classSpecificReferenceForm.setNestedEntries(byteCode, operandManager, offset);

        verify(byteCode).setNested(new ClassFileEntry[]{classFileEntry});
        verify(byteCode).setNestedPositions(new int[][]{{0, 2}});
    }
}